package org.hsweb.generator.swing.panel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.hsweb.commons.StringUtils;
import org.hsweb.ezorm.meta.TableMetaData;
import org.hsweb.generator.CodeMeta;
import org.hsweb.generator.CodeTemplate;
import org.hsweb.generator.app.register.CodeTemplateRegister;
import org.hsweb.generator.app.register.MetaRegister;
import org.hsweb.generator.app.register.PropertiesRegister;
import org.hsweb.generator.app.register.Wrapper;
import org.hsweb.generator.config.ConfigUtils;
import org.hsweb.generator.db.DatabaseFactory;
import org.hsweb.generator.db.FormCodeMeta;
import org.hsweb.generator.db.template.TableTemplateInput;
import org.hsweb.generator.db.template.TableTemplateOutput;
import org.hsweb.generator.freemaker.template.FreemarkerTemplateDynamicOutput;
import org.hsweb.generator.swing.SwingGeneratorApplication;
import org.hsweb.generator.swing.panel.support.FileChooserCellEditor;
import org.hsweb.generator.swing.panel.support.ShortCutsAdapter;
import org.hsweb.generator.swing.panel.support.ShortCutsListener;
import org.hsweb.generator.swing.utils.JTableUtils;
import org.hsweb.generator.swing.utils.clipboard.ClipboardUtils;
import org.hsweb.generator.template.FileTemplateInput;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.Serializable;
import java.util.*;
import java.util.List;

/**
 * Created by 浩 on 2016-03-18 0018.
 */
public class GeneratorConfigPanel extends LayoutGeneratorPanel {

    /**
     * 表结构表格：列名数组
     */
    protected Object columnNames[];
    /**
     * 表结构表格：列名和字段映射
     */
    protected String filedMapper[];
    /**
     * 表结构表格：新增时的默认值
     */
    protected Object headerDefault[];
    /**
     * 表结构表格的完整配置
     */
    protected JSONObject headerConfig;

    protected final String templateColumnNames[] = {"模板名称", "模板类型", "备注", "输入", "输出"};
    protected final String templateColumnMapper[] = {"name", "type", "comment", "input", "output"};

    private Component[][] components;

    private JTable fieldTable = null, templateTable = null;

    private ShortCutsAdapter fieldTableShortCuts = new ShortCutsAdapter();

    private ShortCutsAdapter templateTableShortCuts = new ShortCutsAdapter();

    @Override
    public String getTooltip() {
        return "模板配置";
    }

    @Override
    public String getTitle() {
        return "模板配置";
    }


    @Override
    public void init(SwingGeneratorApplication application) {
        //加载表结构配置表格的表头数据
        try {
            String json = new ConfigUtils().loadConfigString("config/header.cfg.json");
            JSONObject config = JSON.parseObject(json, Feature.OrderedField);
            headerConfig = config.getJSONObject("meta.header");
            filedMapper = headerConfig.keySet().toArray(new String[headerConfig.size()]);
            columnNames = new Object[filedMapper.length];
            headerDefault = new Object[filedMapper.length];
            for (int i = 0; i < filedMapper.length; i++) {
                JSONObject meta = headerConfig.getJSONObject(filedMapper[i]);
                columnNames[i] = meta.getString("title");
                Object def = meta.get("default");
                if (null != def) {
                    headerDefault[i] = def;
                } else {
                    if ("boolean".equals(meta.getString("type"))) {
                        headerDefault[i] = false;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("获取表头配置失败,请检查config/header.cfg.json文件!", e);
        }
        super.init(application);
        createComponents();
        layoutComponents();
        MetaRegister codeMetaRegister = application.getRegister(MetaRegister.class);
        CodeTemplateRegister templateRegister = application.getRegister(CodeTemplateRegister.class);
        if (codeMetaRegister == null) {
            throw new RuntimeException("MetaRegister not found!");
        }
        if (templateRegister == null) {
            throw new RuntimeException("CodeTemplateRegister not found!");
        }
        codeMetaRegister.register(new Wrapper<List<CodeMeta>>() {
            @Override
            public List<CodeMeta> get() {
                return buildCodeMeta();
            }
        });
        templateRegister.register(new Wrapper<List<CodeTemplate>>() {
            @Override
            public List<CodeTemplate> get() {
                return buildCodeTemplate();
            }
        });
    }

    protected List<CodeMeta> buildCodeMeta() {
        int rows = fieldTable.getRowCount();
        List<CodeMeta> codeMetas = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            FormCodeMeta meta = new FormCodeMeta();
            for (int i1 = 0; i1 < filedMapper.length; i1++) {
                Object o = fieldTable.getValueAt(i, i1);
                String attrName = filedMapper[i1];
                if (StringUtils.isNullOrEmpty(o)) {
                    if (headerDefault.length > i1) {
                        o = headerDefault[i1];
                    }
                }
                meta.setProperty(attrName, o);
            }
            codeMetas.add(meta);
        }
        return codeMetas;
    }

    protected List<CodeTemplate> buildCodeTemplate() {
        //获取模板配置信息
        int rows = templateTable.getRowCount();
        List<Map<String, Object>> rowData = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            Map<String, Object> row = new HashMap<>();
            for (int i1 = 0; i1 < templateColumnMapper.length; i1++) {
                row.put(templateColumnMapper[i1], templateTable.getValueAt(i, i1));
            }
            rowData.add(row);
        }
        //获取变量配置
        PropertiesRegister propertiesRegister = application.getRegister(PropertiesRegister.class);
        Properties config = propertiesRegister.getMergedData();
        logger.debug("获取配置:" + config);
        Map<String, Object> var = new HashMap<>();
        for (Map.Entry<Object, Object> configEntry : config.entrySet()) {
            var.put(String.valueOf(configEntry.getKey()), configEntry.getValue());
        }
        //生成表定义数据
        TableMetaData metaData = new TableMetaData();
        String tableName = config.getProperty("table.name");
        String tableComment = config.getProperty("table.comment");
        String tableAlias = config.getProperty("table.alias");
        var.put("tableMeta", metaData);

        metaData.setName(tableName);
        metaData.setComment(tableComment);
        metaData.setAlias(tableAlias);
        for (CodeMeta meta : buildCodeMeta()) {
            metaData.addField(((FormCodeMeta) meta).getMetaData());
        }

        List<CodeTemplate> codeTemplates = new ArrayList<>();
        for (Map<String, Object> row : rowData) {
            CodeTemplate template = new CodeTemplate();
            template.setName(String.valueOf(row.get("name")));
            if ("freemarker".equals(row.get("type"))) {
                FileTemplateInput input = new FileTemplateInput(new File(String.valueOf(row.get("input"))));
                input.setConfig(var);
                template.setInput(input);
                template.setOutput(new FreemarkerTemplateDynamicOutput(String.valueOf(row.get("output"))));
            } else if ("数据库操作".equals(row.get("type"))) {
                template.setInput(new TableTemplateInput(metaData));
                template.setOutput(new TableTemplateOutput(DatabaseFactory.createDatabase(config)));
            }
            codeTemplates.add(template);
        }
        return codeTemplates;
    }

    private void createTemplateTable() {
        final Object[][] cellData = new Object[][]{};
        final DefaultTableModel model = new DefaultTableModel(cellData, templateColumnNames);
        templateTable = new JTable(model) {
            {
                getColumn("模板类型").setCellEditor(new DefaultCellEditor(new JComboBox() {{
                    this.addItem("freemarker");
                    this.addItem("数据库操作");
                }}));
                //输入
                FileChooserCellEditor input = new FileChooserCellEditor(new JTextField()) {
                    @Override
                    public boolean isFileOnly() {
                        return true;
                    }
                };
                input.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        return true;
                    }

                    @Override
                    public String getDescription() {
                        return "选择模板文件";
                    }
                });
                getColumn("输入").setCellEditor(input);
                //输出目录
                FileChooserCellEditor output = new FileChooserCellEditor(new JTextField());
                output.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        if (f.isDirectory())
                            return true;
                        return false;
                    }

                    @Override
                    public String getDescription() {
                        return "选择输出目录";
                    }
                });
                getColumn("输出").setCellEditor(output);
                setSize(SwingGeneratorApplication.WIDTH - 70, 190);
                setRowMargin(2);
                setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
                setRowHeight(22);
                setSelectionBackground(new Color(227, 227, 227));
            }
        };
        initTemplateTableShortCuts();
        //设置当失去焦点时取消编辑
        templateTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        templateTable.addKeyListener(templateTableShortCuts);
        //此监听器用于解决当按下快捷键删除行操作时，导致表格进入编辑状态。
        templateTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                e.consume();
            }
        });
    }

    private void createFieldTable() {
        final Object[][] cellData = new Object[][]{};
        final DefaultTableModel model = new DefaultTableModel(cellData, columnNames);
        fieldTable = new JTable(model) {
            {
                for (String field : filedMapper) {
                    DefaultCellEditor editor;
                    JSONObject meta = headerConfig.getJSONObject(field);
                    String type = meta.getString("type");
                    if (type == null) type = "string";
                    final boolean isBoolean = "boolean".equals(type);
                    if (isBoolean) {
                        meta.put("option_list", Arrays.asList(true, false));
                        type = "select";
                    }
                    if ("select".equals(type)) {
                        JComboBox comboBox = new JComboBox();
                        JSONArray array = meta.getJSONArray("option_list");
                        if (array != null)
                            array.forEach(comboBox::addItem);
                        editor = new DefaultCellEditor(comboBox);
                    } else {
                        editor = new DefaultCellEditor(new JTextField());
                    }
                    getColumn(meta.getString("title")).setCellEditor(editor);
                }
                setSize(SwingGeneratorApplication.WIDTH - 70, 190);
                setRowMargin(2);
                setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
                setRowHeight(20);
                setSelectionBackground(new Color(227, 227, 227));
            }
        };
        initFieldTableShortCuts();
        fieldTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        fieldTable.addKeyListener(fieldTableShortCuts);
        fieldTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                e.consume();
            }
        });
        fieldTable.getModel().addTableModelListener(e -> {
            if (e.getColumn() >= 0 && e.getFirstRow() == e.getLastRow()) {
                DefaultTableModel model1 = ((DefaultTableModel) e.getSource());
                String field = filedMapper[e.getColumn()];
                JSONObject fieldObj = headerConfig.getJSONObject(field);
                Object matchesValue = model1.getValueAt(e.getFirstRow(), e.getColumn());
                for (Map.Entry<String, Object> entry : fieldObj.entrySet()) {
                    if (entry.getKey().startsWith("mapper.")) {
                        //关联了另外一个字段
                        String targetField = entry.getKey().split("[.]")[1];
                        int targetIndex = Arrays.asList(filedMapper).indexOf(targetField);
                        JSONObject mapper = ((JSONObject) entry.getValue());
                        for (Map.Entry<String, Object> tmp : mapper.entrySet()) {
                            //要匹配的值
                            if (String.valueOf(matchesValue).matches(tmp.getKey())) {
                                model1.setValueAt(tmp.getValue(), e.getFirstRow(), targetIndex);
                                break;
                            }
                        }
                    }
                }
            }
        });
    }

    protected void createComponents() {
        createFieldTable();
        createTemplateTable();
        components = new Component[][]{
                //第一行
                {
                        new JButton("添加") {{
                            setSize(80, 25);
                            setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
                            addActionListener(e -> ((DefaultTableModel) fieldTable.getModel()).addRow(headerDefault));
                        }},
                        new JButton("删除") {{
                            setSize(80, 25);
                            setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
                            addActionListener(e -> JTableUtils.removeSelectedRows(fieldTable));
                        }}
//                        ,
//                        new JButton("导入excel") {{
//                            setSize(80, 25);
//                            setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
//                            setMargin(new Insets(0, 0, 0, 0));
//                            addActionListener(new ActionListener() {
//                                @Override
//                                public void actionPerformed(ActionEvent e) {
//                                    JFileChooser chooser = new JFileChooser();
//                                    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//                                    chooser.setFileFilter(new FileFilter() {
//                                        @Override
//                                        public boolean accept(File f) {
//                                            if (f.isDirectory()) return true;
//                                            return f.getName().endsWith("xls") || f.getName().endsWith("xlsx");
//                                        }
//
//                                        @Override
//                                        public String getDescription() {
//                                            return "excel文档";
//                                        }
//                                    });
//                                    chooser.setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
//                                    chooser.showOpenDialog(null);
//                                    File f = chooser.getSelectedFile();
//                                    if (f == null)
//                                        return;
//                                    if (f.getName() != "xls" && f.getName() != "xlsx") {
//                                        try {
//                                            java.util.List<Map<String, Object>> datas = ExcelIO.read2Map(new FileInputStream(f));
//                                            logger.info("导入excel成功!");
//                                        } catch (Exception e1) {
//                                            logger.error("加载文件失败", e1);
//                                        }
//                                    } else {
//                                        logger.info("格式错误，只支持xls和xlsx格式的文件！");
//                                    }
//
//                                }
//                            });
//                        }}
                },
                //第二行
                {
                        new JScrollPane() {{
                            setSize(SwingGeneratorApplication.WIDTH - 50, 200);
                            setViewportView(fieldTable);
                        }}
                },
                //第三行
                {
                        new JButton("添加") {{
                            setSize(80, 25);
                            setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
                            addActionListener(e -> ((DefaultTableModel) templateTable.getModel()).addRow(new Object[]{"Controller", "freemarker", "新建模板", ""}));
                        }},
                        new JButton("删除") {{
                            setSize(80, 25);
                            setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
                            addActionListener(e -> JTableUtils.removeSelectedRows(templateTable));
                        }},
                }
                ,
                //第四行
                {
                        new JScrollPane() {{
                            setSize(SwingGeneratorApplication.WIDTH - 50, 200);
                            setViewportView(templateTable);
                        }}
                }
        };
    }

    protected void initTemplateTableShortCuts() {
        templateTableShortCuts.bind("Ctrl+V", () -> {});
        templateTableShortCuts.bind("Ctrl+D", () -> JTableUtils.removeSelectedRows(templateTable));
    }

    /**
     * 将二维数组转换为List
     */
    protected List<Map<String, Object>> transFieldTableData(Object[][] datas, String[] field) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (Object[] data : datas) {
            Map<String, Object> map = new LinkedHashMap<>();
            for (int i = 0; i < field.length; i++) {
                String key = field[i];
                Object value;
                if (data.length > i) {
                    value = data[i];
                    JSONObject conf = headerConfig.getJSONObject(key);
                    boolean lowerCase = conf.getBooleanValue("lowerCase");
                    if (lowerCase) value = String.valueOf(value).toLowerCase();
                    if ("boolean".equals(conf.getString("type"))) {
                        value = String.valueOf(value).toLowerCase().contains("true");
                    }
                } else {
                    value = "";
                }
                map.put(key, value);
            }
            list.add(map);
        }
        return list;
    }

    protected void initFieldTableShortCuts() {
        this.addKeyListener(fieldTableShortCuts);
        fieldTableShortCuts.bind("Ctrl+V", () -> {
            try {
                Object[][] data = ClipboardUtils.getClipboardAsTableData();
                List<Map<String, Object>> datas = transFieldTableData(data, filedMapper);
                for (Map<String, Object> map : datas) {
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        //如果字段的值为null时，将尝试使用其他的字段值进行映射转换
                        if (StringUtils.isNullOrEmpty(value)) {
                            JSONObject jsonObject = headerConfig.getJSONObject(key);
                            String bind = jsonObject.getString("mapperBind");
                            if (StringUtils.isNullOrEmpty(bind)) {
                                //如果没有配置映射字段，将使用默认值
                                value = jsonObject.get("default");
                            } else {
                                JSONObject bindField = headerConfig.getJSONObject(bind);
                                if (bindField != null) {
                                    JSONObject mapper = bindField.getJSONObject("mapper.".concat(key));
                                    for (Map.Entry<String, Object> tmp : mapper.entrySet()) {
                                        //要匹配的值
                                        Object matchesValue = map.get(bind);
                                        if (String.valueOf(matchesValue).matches(tmp.getKey())) {
                                            value = tmp.getValue();
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        entry.setValue(value);
                    }
                    ((DefaultTableModel) fieldTable.getModel()).addRow(map.values().toArray());
                }
            } catch (Exception e) {
                logger.error("获取剪切板数据失败", e);
            }
        });
        fieldTableShortCuts.bind("Ctrl+D", () -> JTableUtils.removeSelectedRows(fieldTable));
    }

    @Override
    public void onSelected() {

    }

    @Override
    public Component[][] getComponentArray() {
        return components;
    }

    @Override
    public void load(Serializable o) {
        if (o instanceof ArrayList) {
            int rowCount = templateTable.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                ((DefaultTableModel) templateTable.getModel()).removeRow(i);
            }
            ((ArrayList<Object[]>) o).forEach(((DefaultTableModel) templateTable.getModel())::addRow);
        }
    }

    @Override
    public Serializable getConfig() {
        int rowCount = templateTable.getRowCount();
        ArrayList<Object[]> arrayList = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            Object[] row = new Object[templateColumnNames.length];
            for (int x = 0; x < templateColumnNames.length; x++) {
                row[x] = templateTable.getValueAt(i, x);
            }
            arrayList.add(row);
        }
        return arrayList;
    }

    @Override
    public String getConfigName() {
        return "config/template.cfg";
    }
}
