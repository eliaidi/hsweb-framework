package org.hsweb.generator.app.register;

import java.util.Properties;

/**
 * 配置注册器
 */
public class PropertiesRegister extends AbstractRegister<Properties> {
    /**
     * 合并获取配置，<b style="color:red">注意：相同的key将会被覆盖</b>
     *
     * @return 配置对象
     */
    @Override
    public Properties getMergedData() {
        Properties merged = new Properties();
        for (Properties properties : getDataList()) {
            merged.putAll(properties);
        }
        return merged;
    }
}
