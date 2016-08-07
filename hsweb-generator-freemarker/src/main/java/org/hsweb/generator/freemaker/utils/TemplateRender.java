package org.hsweb.generator.freemaker.utils;

import java.util.Map;

public interface TemplateRender {

    String render(Map<String, Object> vars) throws Exception;
}
