package org.hsweb.generator;

/**
 * Created by 浩 on 2016-03-20 0020.
 */
public interface CodeMeta {
    <T> T getProperty(String property);

    <T> T setProperty(String property, T object);
}
