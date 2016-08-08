package org.hsweb.web.bean.po.script;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hsweb.web.bean.po.GenericPo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 动态脚本
 */
public class DynamicScript extends GenericPo<String> {
    private static final long serialVersionUID = 8910856253780046561L;
    //名称
    @NotNull
    @Length(min = 4, message = "名称长度不能少于4")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "名称只能为大小写字母,数字,下划线和-组成")
    private String name;
    //类型
    @Pattern(regexp = "(js)|(groovy)|(java)|(javascript)", message = "类型仅支持js(javascript),groovy,java")
    private String type;
    //内容
    @NotBlank(message = "脚本内容不能为空")
    private String content;
    //备注
    private String remark;
    //分类
    private String classifiedId;
    //状态
    private int status;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        if (this.type == null)
            return "js";
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        if (this.content == null)
            return "";
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        if (this.remark == null)
            return "";
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setClassifiedId(String classifiedId) {
        this.classifiedId = classifiedId;
    }

    public String getClassifiedId() {
        return classifiedId;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
