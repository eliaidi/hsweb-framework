package org.hsweb.generator.db.template;

import org.hsweb.ezorm.meta.TableMetaData;
import org.hsweb.generator.template.TemplateInput;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态表单模板输入，设置String类型的模板，通过动态表单定义对象作为配置项（参数 table），进行模板生成。
 */
public class TableTemplateInput implements TemplateInput {
    private TableMetaData metaData;
    private String template;

    private Map<String, Object> config = new HashMap<>();

    public TableTemplateInput(TableMetaData metaData) {
        this.metaData = metaData;
    }

    public TableTemplateInput(TableMetaData metaData, String template) {
        this.metaData = metaData;
        this.template = template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    @Override
    public String read() {
        return template;
    }

    public void setConfig(Map<String, Object> config) {
        this.config = config;
    }

    @Override
    public Map<String, Object> getConfig() {
        config.put("table", metaData);
        return config;
    }
}
