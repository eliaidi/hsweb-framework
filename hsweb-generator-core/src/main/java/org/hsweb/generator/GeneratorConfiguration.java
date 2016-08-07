package org.hsweb.generator;


import org.hsweb.generator.callback.GeneratorProcessCallBack;

import java.util.LinkedList;
import java.util.List;

/**
 * 代码生成配置
 */
public class GeneratorConfiguration {
    //配置名字
    private String name;

    //模板列表
    private List<CodeTemplate> templates = new LinkedList<>();

    //生成回调
    private GeneratorProcessCallBack callBack = (step, message) -> {};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CodeTemplate> getTemplates() {
        return templates;
    }

    public void setTemplates(List<CodeTemplate> templates) {
        this.templates = templates;
    }

    public GeneratorProcessCallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(GeneratorProcessCallBack callBack) {
        this.callBack = callBack;
    }

    public GeneratorConfiguration addTemplate(CodeTemplate template) {
        templates.add(template);
        return this;
    }
}
