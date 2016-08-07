package org.hsweb.generator.app.register;

/**
 * 数据包装器，调用包装器获取数据
 * Created by 浩 on 2016-03-18 0018.
 */
public interface Wrapper<T> {
    /**
     * 获取包装器提供的数据
     */
    T get();
}
