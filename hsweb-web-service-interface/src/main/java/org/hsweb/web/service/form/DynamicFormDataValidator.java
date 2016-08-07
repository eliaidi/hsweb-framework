package org.hsweb.web.service.form;

import java.util.Map;

public interface DynamicFormDataValidator {

    String getRepeatDataId(String tableName, Map<String, Object> data);
}
