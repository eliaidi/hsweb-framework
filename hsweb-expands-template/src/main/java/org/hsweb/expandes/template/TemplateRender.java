package org.hsweb.expandes.template;

import java.util.Map;

public interface TemplateRender {

    String render(Map<String, Object> vars) throws Exception;
}
