package org.hsweb.generator;

import org.hsweb.generator.template.TemplateInput;
import org.hsweb.generator.template.TemplateOutput;

import java.io.Serializable;

/**
 * 代码模板
 */
public class CodeTemplate implements Serializable {
    /**
     * 模板ID
     */
    private String id;

    /**
     * 模板名字
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 模板输入
     */
    private TemplateInput input;

    /**
     * 模板输出
     */
    private TemplateOutput output;


    public CodeTemplate() {
    }

    public CodeTemplate(String id, String name, TemplateInput input, TemplateOutput output) {
        this(id, name, "", input, output);
    }

    public CodeTemplate(String id, String name, String remark, TemplateInput input, TemplateOutput output) {
        this.id = id;
        this.name = name;
        this.remark = remark;
        setOutput(output);
        setInput(input);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TemplateInput getInput() {
        return input;
    }

    public void setInput(TemplateInput input) {
        this.input = input;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setOutput(TemplateOutput output) {
        this.output = output;
        output.setTemplate(this);
    }

    public TemplateOutput getOutput() {
        return output;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
