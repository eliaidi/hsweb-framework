package org.hsweb.generator;

public interface CodeMeta {

    <T> T getProperty(String property);

    <T> T setProperty(String property, T object);
}
