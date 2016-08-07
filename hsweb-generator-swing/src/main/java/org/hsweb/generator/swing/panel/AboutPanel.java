package org.hsweb.generator.swing.panel;

import org.hsweb.generator.swing.SwingGeneratorApplication;
import org.hsweb.generator.swing.panel.support.ShortCutsAdapter;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

/**
 * Created by 浩 on 2016-03-19 0019.
 */
public class AboutPanel extends LayoutGeneratorPanel {
    private Component[][] components;

    protected JTable table;

    protected final String columnNames[] = {"快捷键", "功能"};

    //设置快捷键支持
    protected ShortCutsAdapter shortCutsAdapter;
    /**
     * 默认配置
     */
    private Object[][] defaultData = new Object[][]{
            {"Ctrl+V", "从剪贴板粘贴数据,支持文本、文件粘贴。"},
            {"Ctrl+D", "删除当前选中的行。"},
    };

    public void createComponents() {
        JLabel label = createLabel("<html>1、贡献代码-><a target='_blank' href=\"https://github.com/hs-web/hsweb-generator\">https://github.com/hs-web/hsweb-generator</a></html>");
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                java.awt.Desktop dp = java.awt.Desktop.getDesktop();
                if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                    try {
                        dp.browse(new URI("https://github.com/hs-web/hsweb-generator"));
                    } catch (Exception e1) {
                    }
                }
            }
        });
        components = new Component[][]{
                {
                        label
                }
                ,
                {
                        createLabel("2、快捷键列表:")
                }
                , {
                new JScrollPane() {{
                    setSize(SwingGeneratorApplication.WIDTH - 50, 300);
                    setViewportView(table);
                }}
        }
        };
    }

    /**
     * 创建表格
     */
    protected void createTable() {
        final Object[][] cellData = new Object[][]{};
        final DefaultTableModel model = new DefaultTableModel(cellData, columnNames);
        final DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setDefaultRenderer(Object.class,render);
        table.setSize(SwingGeneratorApplication.WIDTH - 70, 300);
        table.setRowMargin(4);
        table.setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
        table.setRowHeight(25);
        table.setSelectionBackground(new Color(227, 227, 227));
        for (Object[] objects : defaultData) {
            model.addRow(objects);
        }
    }

    protected JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(SwingGeneratorApplication.BASIC_FONT);
        label.setSize(text.length() * (label.getFont().getSize()), 30);
        return label;
    }

    @Override
    public void init(SwingGeneratorApplication application) {
        super.init(application);
        createTable();
        createComponents();
        layoutComponents();
    }

    @Override
    public Component[][] getComponentArray() {
        return components;
    }

    @Override
    public String getTooltip() {
        return "关于/帮助";
    }

    @Override
    public String getTitle() {
        return "关于/帮助";
    }

    @Override
    public void onSelected() {

    }
}
