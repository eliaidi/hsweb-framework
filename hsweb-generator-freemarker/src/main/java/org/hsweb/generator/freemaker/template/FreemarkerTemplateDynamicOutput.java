package org.hsweb.generator.freemaker.template;

import org.hsweb.generator.freemaker.utils.TemplateUtils;

import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * 基于freemarker的动态输出。输出路径可设置变量。
 */
public class FreemarkerTemplateDynamicOutput extends FreemarkerTemplateOutput {


    private String output;

    public FreemarkerTemplateDynamicOutput(String output) {
        this.output = output;
    }

    @Override
    public OutputStream getOutputStream() throws Exception {
        String outputPath = TemplateUtils.compile(output).render(template.getInput().getConfig());
        logger.debug("创建模板输出目录:{}", outputPath);
        new java.io.File(outputPath).getParentFile().mkdirs();
        return new FileOutputStream(outputPath);
    }
}
