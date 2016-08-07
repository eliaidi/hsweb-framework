package org.hsweb.generator.config;

import org.hsweb.commons.file.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class ConfigUtils {
    public String savePath = System.getProperty("user.dir");
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public String loadConfigString(String name) throws Exception {
        File configFile = new File(savePath.concat(File.separator).concat(name));
        return FileUtils.reader2String(configFile.getAbsolutePath());
    }

    public void loadConfig(Configurable configurable) throws Exception {
        String configName = configurable.getConfigName();
        File configFile = new File(savePath.concat(File.separator).concat(configName));
        try {
            Serializable object = FileUtils.readFile2Obj(configFile.getAbsolutePath());
            configurable.load(object);
            logger.debug("加载配置:" + configFile);
        } catch (FileNotFoundException e) {

        }
    }

    public void saveConfig(Configurable configurable) throws Exception {
        Serializable data = configurable.getConfig();
        if (data == null) return;
        String configName = configurable.getConfigName();
        File configFile = new File(savePath.concat(File.separator).concat(configName));
        logger.debug("保存配置:" + configFile);
        FileUtils.writeObj2File(data, configFile.getAbsolutePath());
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }
}
