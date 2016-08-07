package org.hsweb.web.bean.po;


import org.hsweb.commons.MD5;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 通用的PO对象
 */
public class GenericPo<PK> implements Serializable {
    private static final long serialVersionUID = 9197157871004374522L;
    /**
     * 主键
     */
    private PK id;
    /**
     * 自定义属性
     */
    private Map<String, Object> properties;

    public PK getId() {
        return id;
    }

    public void setId(PK id) {
        this.id = id;
    }

    public <T> T setProperty(String attr, T value) {
        if (properties == null) properties = new LinkedHashMap<>();
        properties.put(attr, value);
        return value;
    }

    public <T> T getProperty(String attr) {
        if (properties == null) return null;
        return ((T) properties.get(attr));
    }

    @Override
    public int hashCode() {
        if (getId() == null) return 0;
        return getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        return this.hashCode() == obj.hashCode();
    }


    /**
     * 创建一个主键
     */
    public static String createUID() {
        return MD5.encode(UUID.randomUUID().toString());
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
