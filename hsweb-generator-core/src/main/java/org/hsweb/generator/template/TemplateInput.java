package org.hsweb.generator.template;

import java.util.Map;

/**
 * 模板输入接口,用于获取模板内容以及配置
 */
public interface TemplateInput {
    String read() throws Exception;

    Map<String, Object> getConfig();
}
