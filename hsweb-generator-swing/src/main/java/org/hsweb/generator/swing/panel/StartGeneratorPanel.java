package org.hsweb.generator.swing.panel;

import org.hsweb.generator.CodeTemplate;
import org.hsweb.generator.Generator;
import org.hsweb.generator.GeneratorConfiguration;
import org.hsweb.generator.app.register.CodeTemplateRegister;
import org.hsweb.generator.swing.SwingGeneratorApplication;
import org.hsweb.generator.swing.logger.JTextAreaLoggerAppender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 浩 on 2016-03-18 0018.
 */
public class StartGeneratorPanel extends LayoutGeneratorPanel {

    protected Component components[][];

    protected JTextArea console;

    public void createComponents() {
        console = new JTextArea() {{
            setSize(SwingGeneratorApplication.WIDTH - 50, 150);
            setText(">控制台准备就绪!\n");
            setAutoscrolls(true);
            setEditable(false);
            setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
            setForeground(Color.black);
        }};
        JTextAreaLoggerAppender.bindTextArea(console);
        components = new Component[][]{
                {
                        new JButton("开始") {{
                            setSize(80, 25);
                            setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
                            addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        startGenerator();
                                    } catch (Exception e1) {
                                        logger.error("生成代码失败:", e1);
                                    }
                                }
                            });
                        }}
                        ,
                        new JButton("清空控制台") {{
                            setSize(150, 25);
                            setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
                            addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    console.setText(">控制台准备就绪!\n");
                                }
                            });
                        }}
                },
                {},
                {
                        new JScrollPane() {{
                            setSize(SwingGeneratorApplication.WIDTH - 50, 400);
                            setViewportView(console);
                        }}
                }
        };
    }

    @Override
    public void init(SwingGeneratorApplication application) {
        super.init(application);
        createComponents();
        layoutComponents();
    }

    @Override
    public Component[][] getComponentArray() {
        return components;
    }

    @Override
    public String getTooltip() {
        return "生成代码";
    }

    @Override
    public String getTitle() {
        return "生成代码";
    }

    @Override
    public void onSelected() {

    }

    protected void startGenerator() throws Exception {
        CodeTemplateRegister codeTemplateRegister = application.getRegister(CodeTemplateRegister.class);
        java.util.List<CodeTemplate> codeTemplates = codeTemplateRegister.getMergedData();
        logger.debug("获取模板:" + codeTemplates);
        GeneratorConfiguration configuration = new GeneratorConfiguration();
        configuration.setTemplates(codeTemplates);
        logger.debug("开始生成...");
        Generator.execute(configuration);
        application.saveConfig();
    }
}
