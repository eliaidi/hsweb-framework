package org.hsweb.web.bean.po.config;

import com.alibaba.fastjson.JSON;
import org.hsweb.web.bean.po.GenericPo;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

/**
 * 系统配置
 */
public class Config extends GenericPo<String> {
    private static final long serialVersionUID = 5328848488856425388L;

    //备注
    private String remark;

    //配置内容
    private String content;

    //创建日期
    private java.util.Date createDate;

    //最后一次修改日期
    private java.util.Date updateDate;

    //配置分类ID
    private String classifiedId;

    public String getRemark() {
        if (this.remark == null)
            return "";
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContent() {
        if (this.content == null)
            return "";
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public java.util.Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取 最后一次修改日期
     *
     * @return java.util.Date 最后一次修改日期
     */
    public java.util.Date getUpdateDate() {
        return this.updateDate;
    }

    /**
     * 设置 最后一次修改日期
     */
    public void setUpdateDate(java.util.Date updateDate) {
        this.updateDate = updateDate;
    }


    public String getClassifiedId() {
        return classifiedId;
    }

    public void setClassifiedId(String classifiedId) {
        this.classifiedId = classifiedId;
    }

    public Map<Object, Object> toMap() {
        if (getContent().trim().startsWith("{")) {
            return JSON.parseObject(getContent(), Map.class);
        }
        Map<Object, Object> data = new LinkedHashMap<>();
        toList().forEach(map -> data.put(map.get("key"), map.get("value")));
        return data;
    }

    public List<Map<Object, Object>> toList() {
        List<Map<Object, Object>> array = (List) JSON.parseArray(getContent(), Map.class);
        return array;
    }
}
