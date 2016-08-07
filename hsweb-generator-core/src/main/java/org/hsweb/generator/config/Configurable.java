package org.hsweb.generator.config;

import java.io.Serializable;

/**
 * Created by 浩 on 2016-03-21 0021.
 */
public interface Configurable<T extends Serializable> {
    void load(T o);

    T getConfig();

    String getConfigName();
}
