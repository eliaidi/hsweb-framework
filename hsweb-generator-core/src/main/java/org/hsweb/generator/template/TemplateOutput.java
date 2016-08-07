package org.hsweb.generator.template;


import org.hsweb.generator.CodeTemplate;

/**
 * 模板输出接口
 */
public interface TemplateOutput {
    void output()  throws Exception;

    void setTemplate(CodeTemplate template);
}
