package org.hsweb.generator.freemaker.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.hsweb.commons.StringUtils;
import org.hsweb.generator.config.ConfigUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

public class TemplateUtils {
    //freemarker字符串模板加载器
    private static final StringTemplateLoader loader = new StringTemplateLoader();
    //freemarker配置器
    private static final Configuration freemarkerCfg = new Configuration(Configuration.VERSION_2_3_23);
    private static Logger logger = LoggerFactory.getLogger(TemplateUtils.class);

    static {
        String charset = "UTF-8";
        try {
            String json = new ConfigUtils().loadConfigString("config/encode.cfg.json");
            JSONObject jsonObject = JSON.parseObject(json);
            charset = jsonObject.getString("template.out.encoding");
        } catch (Exception e) {
            logger.warn("template.out.encoding not found,{}! use UTF-8.", e.getMessage());
        }
        logger.info("use template.out.encoding={}", charset);
        //初始化
        freemarkerCfg.setEncoding(Locale.getDefault(), charset);
        freemarkerCfg.setTemplateLoader(loader);
    }

    private static MethodUtils methodUtils = new MethodUtils();

    /**
     * 编译模板并装载模板
     *
     * @param templateStr 模板内容
     * @return 编译是否成功
     * @throws Exception 异常信息，如模板未找到
     */
    public static TemplateRender compile(String templateStr) throws Exception {
        StringTemplateLoader templateLoader = ((StringTemplateLoader) freemarkerCfg.getTemplateLoader());
        String name = StringUtils.concat("template.", templateStr.hashCode());
        templateLoader.putTemplate(name, templateStr);
        freemarkerCfg.setTemplateLoader(templateLoader);
        final String finalName = name;
        return new TemplateRender() {
            @Override
            public String render(Map<String, Object> vars) throws Exception {
                Template template = freemarkerCfg.getTemplate(finalName);
                StringWriter out = new StringWriter();
                vars.put("utils", methodUtils);
                template.process(vars, out);

                return out.getBuffer().toString();
            }
        };
    }
}
