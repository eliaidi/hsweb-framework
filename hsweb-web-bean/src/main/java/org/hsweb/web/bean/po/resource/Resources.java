package org.hsweb.web.bean.po.resource;

import org.hsweb.commons.file.FileUtils;
import org.hsweb.web.bean.po.GenericPo;

import java.util.Date;

/**
 * 资源
 */
public class Resources extends GenericPo<String> {
    private static final long serialVersionUID = 8910856253780046561L;
    // 资源名称
    private String name;
    // 资源地址
    private String path;
    // 创建时间
    private Date createDate;
    // 创建人主键
    private String creatorId;
    // MD5校验值
    private String md5;
    // 资源类型
    private String type;
    // 资源分类
    private String classified;
    // 文件大小
    private long size;
    // 状态
    private int status;

    public String getClassified() {
        return classified;
    }

    public void setClassified(String classified) {
        this.classified = classified;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getSize() {
        return size;
    }

    public String getName() {
        if (this.name == null)
            return "";
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        if (this.path == null)
            return "";
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreatorId() {
        if (this.creatorId == null)
            return "";
        return this.creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getMd5() {
        if (this.md5 == null)
            return "";
        return this.md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getType() {
        if (this.type == null)
            return "";
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSuffix() {
        return FileUtils.getSuffix(getName());
    }
}
