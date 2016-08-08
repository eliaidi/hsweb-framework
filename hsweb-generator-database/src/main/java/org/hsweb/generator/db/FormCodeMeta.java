package org.hsweb.generator.db;

import org.apache.commons.beanutils.BeanUtils;
import org.hsweb.ezorm.meta.FieldMetaData;
import org.hsweb.generator.CodeMeta;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FormCodeMeta implements CodeMeta {
    private FieldMetaData metaData = new FieldMetaData();
    private static final Map<String, Class> typeMapper = new HashMap<>();
    static {
        typeMapper.put("string", String.class);
        typeMapper.put("String", String.class);
        typeMapper.put("byte", byte.class);
        typeMapper.put("short", short.class);
        typeMapper.put("char", char.class);
        typeMapper.put("long", long.class);
        typeMapper.put("float", float.class);
        typeMapper.put("double", double.class);
        typeMapper.put("boolean", boolean.class);
        typeMapper.put("int", int.class);
        typeMapper.put("Integer", Integer.class);
        typeMapper.put("integer", Integer.class);
        typeMapper.put("Boolean", Boolean.class);
        typeMapper.put("Float", Float.class);
        typeMapper.put("Double", Double.class);
        typeMapper.put("Long", Long.class);
        typeMapper.put("Char", Character.class);
        typeMapper.put("Short", Short.class);
        typeMapper.put("date", Date.class);
    }

    @Override
    public <T> T getProperty(String property) {
        Object data = null;
        try {
            data = BeanUtils.getProperty(metaData, property);
        } catch (Exception e) {
        }
        if (data == null) {
            data = metaData.getProperty(property).getValue();
        }
        return ((T) data);
    }

    protected Class getTypeByName(String name) {
        Class type = typeMapper.get(name);
        if (type == null) {
            try {
                type = Class.forName(name);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return type;
    }

    @Override
    public <T> T setProperty(String property, T object) {
        switch (property) {
            case "name":
                metaData.setName(String.valueOf(object));
                break;
            case "comment":
                metaData.setComment(String.valueOf(object));
                break;
            case "javaType":
                metaData.setJavaType(getTypeByName(String.valueOf(object)));
                break;
            case "dataType":
                metaData.setDataType(String.valueOf(object));
                break;
            case "notNull":
                metaData.setProperty("not-null", true);
                break;
            case "primaryKey":
                metaData.setProperty("primary-key", true);
                break;
            default:
                metaData.setProperty(property, object);
                break;
        }
        return object;
    }

    public FieldMetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(FieldMetaData metaData) {
        this.metaData = metaData;
    }
}
