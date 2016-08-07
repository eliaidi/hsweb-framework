package org.hsweb.generator.freemaker.utils;


import org.hsweb.commons.StringUtils;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class MethodUtils {
    private static final Map<Class, String> jdbcTypeMapper = new LinkedHashMap<>();

    static {
        jdbcTypeMapper.put(String.class, "VARCHAR");
        jdbcTypeMapper.put(int.class, "INTEGER");
        jdbcTypeMapper.put(long.class, "BIGINT");
        jdbcTypeMapper.put(double.class, "DOUBLE");
        jdbcTypeMapper.put(boolean.class, "INTEGER");
        jdbcTypeMapper.put(Date.class, "TIMESTAMP");
    }

    public String getJdbcType(Class type) {
        return jdbcTypeMapper.get(type);
    }

    public String getGetter(String name, Class type) {
        name = StringUtils.toUpperCaseFirstOne(name);
        if (Boolean.class == type || boolean.class == type) {
            return "is" + name;
        }
        return "get" + name;
    }

    public String getSetter(String name) {
        name = StringUtils.toUpperCaseFirstOne(name);
        return "set" + name;
    }
}
