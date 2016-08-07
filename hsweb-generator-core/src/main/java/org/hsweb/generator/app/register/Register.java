package org.hsweb.generator.app.register;

/**
 * 注册器接口
 * 生成器应用程序提供此接口的实现。组件获取接口后，应向其注册一个数据包装器
 * <br/>
 * Created by 浩 on 2016-03-18 0018.
 */
public interface Register<T> {

    /**
     * 注册一个数据包装器。
     * 应用在执行生成代码的时候，会调用注册的包装器获取生成代码时需要的数据。
     *
     * @param wrapper 包装器实例
     */
    void register(Wrapper<T> wrapper);
}
