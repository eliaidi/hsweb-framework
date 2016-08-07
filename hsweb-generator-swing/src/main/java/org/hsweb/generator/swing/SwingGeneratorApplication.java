package org.hsweb.generator.swing;

import org.hsweb.generator.app.GeneratorApplication;
import org.hsweb.generator.app.register.CodeTemplateRegister;
import org.hsweb.generator.app.register.MetaRegister;
import org.hsweb.generator.app.register.PropertiesRegister;
import org.hsweb.generator.app.register.Register;
import org.hsweb.generator.config.ConfigUtils;
import org.hsweb.generator.freemaker.utils.TemplateUtils;
import org.hsweb.generator.swing.panel.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * 基于swing的代码生成器
 * Created by 浩 on 2016-03-18 0018.
 */
public class SwingGeneratorApplication extends JFrame implements GeneratorApplication {
    public static final int WIDTH = 800, HEIGHT = 600;

    public static final Font BASIC_FONT = new Font("微软雅黑", Font.BOLD, 18);

    public static final Font BASIC_FONT_MIN = new Font("微软雅黑", Font.BOLD, 13);

    //面板集合
    private java.util.List<GeneratorPanel> panels = new LinkedList<>();

    //注册器集合
    private java.util.Map<Class<? extends Register>, Register> registerMap = new HashMap<>();

    public void registerPanel(GeneratorPanel panel) {
        panels.add(panel);
    }

    public SwingGeneratorApplication() {
        //创建主窗体信息
       // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("代码生成器 v1.0 by github.com/hs-web");
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);//居中
        this.setResizable(false);
        this.setBackground(new Color(43, 43, 43));
        this.setForeground(new Color(43, 43, 43));
        this.setFont(BASIC_FONT);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                shutdown();
            }
        });
        //设置注册器
        setRegister(PropertiesRegister.class, new PropertiesRegister());
        setRegister(CodeTemplateRegister.class, new CodeTemplateRegister());
        setRegister(MetaRegister.class, new MetaRegister());
    }

    protected void renderPanel() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(BASIC_FONT_MIN);
        //加入panel到选项卡
        for (GeneratorPanel panel : panels) {
            tabbedPane.addTab(panel.getTitle(), null, panel, panel.getTooltip());
            panel.init(this);
        }
        //触发选中第一个
        if (panels.size() > 0) {
            panels.get(0).onSelected();
        }
        //添加切换选项卡事件监听器
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = ((JTabbedPane) e.getSource()).getSelectedIndex();
                panels.get(index).onSelected();
            }
        });
        this.add(tabbedPane);
    }

    @Override
    public String getName() {
        return "swing";
    }

    @Override
    public void startup() {
        //注册面板
        registerPanel(new VarPanel());
        registerPanel(new GeneratorConfigPanel());
        registerPanel(new StartGeneratorPanel());
        registerPanel(new AboutPanel());
        //渲染面板
        renderPanel();
        //开启窗口
        this.setVisible(true);
        loadConfig();
    }

    @Override
    public void shutdown() {
        saveConfig();
        System.exit(0);
    }

    @Override
    public <T extends Register> T getRegister(Class<? extends Register> type) {
        return (T) registerMap.get(type);
    }

    public <T extends Register> T setRegister(Class<? extends Register> type, T register) {
        registerMap.put(type, register);
        return register;
    }

    public void saveConfig() {
        for (GeneratorPanel panel : panels) {
            try {
                new ConfigUtils().saveConfig(panel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void loadConfig() {
        for (GeneratorPanel panel : panels) {
            try {
                new ConfigUtils().loadConfig(panel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingGeneratorApplication application = new SwingGeneratorApplication();
        application.startup();
    }
}
