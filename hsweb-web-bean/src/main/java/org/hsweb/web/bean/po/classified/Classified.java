package org.hsweb.web.bean.po.classified;

import org.hsweb.web.bean.po.GenericPo;

public class Classified extends GenericPo<String> {
    // 分类名称
    private String name;
    // 备注
    private String remark;
    // 分类类型
    private String type;
    // 父级分类
    private String parentId;
    // 显示图标
    private String icon;
    // 其他配置
    private String config;
    // 排序
    private int sortIndex;

    public String getName() {
        if (this.name == null)
            return "";
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        if (this.remark == null)
            return "";
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        if (this.type == null)
            return "";
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getIcon() {
        if (this.icon == null)
            return "";
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getConfig() {
        if (this.config == null)
            return "";
        return this.config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }
}
