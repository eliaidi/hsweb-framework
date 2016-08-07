package org.hsweb.generator.template;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.hsweb.commons.file.FileUtils;
import org.hsweb.generator.config.ConfigUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;

/**
 * Created by 浩 on 2016-03-21 0021.
 */
public class FileTemplateInput implements TemplateInput {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public File file;

    private Map<String, Object> config;

    public FileTemplateInput(File file) {
        this();
        this.file = file;
    }

    private String encode = "utf8";

    public FileTemplateInput() {
        try {
            String json = new ConfigUtils().loadConfigString("config/encode.cfg.json");
            JSONObject jsonObject = JSON.parseObject(json);
            encode = jsonObject.getString("template.in.encoding");
        } catch (Exception e) {
            logger.warn("template.in.encoding not found,{}! use {}.", e.getMessage(), encode);
        }
    }

    @Override
    public String read() throws Exception {
        try {
            return FileUtils.reader2String(file.getAbsolutePath());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Map<String, Object> getConfig() {
        return config;
    }

    public void setConfig(Map<String, Object> config) {
        this.config = config;
    }
}
