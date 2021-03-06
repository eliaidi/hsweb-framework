package org.hsweb.web.bean.common;

import java.util.HashMap;
import java.util.Map;

public class UpdateMapParam extends UpdateParam<Map<String, Object>> {
    public UpdateMapParam() {
        this(new HashMap<>());
    }

    public UpdateMapParam(Map<String, Object> data) {
        setData(data);
    }

    public UpdateMapParam set(String key, Object value) {
        this.getData().put(key, value);
        return this;
    }
}
