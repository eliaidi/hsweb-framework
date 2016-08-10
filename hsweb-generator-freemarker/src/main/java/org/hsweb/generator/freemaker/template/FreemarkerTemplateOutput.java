package org.hsweb.generator.freemaker.template;

import org.hsweb.generator.CodeTemplate;
import org.hsweb.generator.freemaker.utils.TemplateRender;
import org.hsweb.generator.freemaker.utils.TemplateUtils;
import org.hsweb.generator.template.TemplateOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;

public abstract class FreemarkerTemplateOutput implements TemplateOutput {
    protected static Logger logger = LoggerFactory.getLogger(FreemarkerTemplateOutput.class);
    protected CodeTemplate template;

    @Override
    public void output() throws Exception {
        // 输出模板到outputStream
        logger.info("开始渲染模板{}", template);
        String tmp = template.getInput().read();
        TemplateRender render = TemplateUtils.compile(tmp);
        String code = render.render(template.getInput().getConfig());
        OutputStream outputStream = getOutputStream();
        outputStream.write(code.getBytes());
        outputStream.flush();
        outputStream.close();
        logger.info("渲染模板{}成功!", template);
    }

    public abstract OutputStream getOutputStream() throws Exception;

    @Override
    public void setTemplate(CodeTemplate template) {
        this.template = template;
    }
}
