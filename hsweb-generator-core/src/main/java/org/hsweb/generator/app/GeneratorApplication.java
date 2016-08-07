package org.hsweb.generator.app;

import org.hsweb.generator.app.register.Register;

/**
 * 代码生成器应用接口，所有代码生成器应用程序都应实现此接口
 * Created by 浩 on 2016-03-18 0018.
 */
public interface GeneratorApplication {
    /**
     * 应用名称
     */
    String getName();

    /**
     * 启动应用
     */
    void startup();

    /**
     * 关闭应用
     */
    void shutdown();

    /**
     * 获取注册器，应用组件通过注册器，向其注册应用所需要的数据。
     * 例如，应用需要一个配置文件，应用程序自身不关心配置所在地，通过调用暴露出的注册器来获取数据即可。
     *
     * @param type 注册器类型
     * @param <T>
     * @return
     */
    <T extends Register> T getRegister(Class<? extends Register> type);

}
