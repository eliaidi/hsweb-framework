package org.hsweb.web.bean.valid;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 对象验证结果集合
 */
public class ValidResults extends ArrayList<ValidResult> implements Serializable {
    private static final long serialVersionUID = 8910856253780046561L;
    /**
     * 是否验证通过
     */
    private boolean success = true;

    @Override
    public boolean addAll(Collection<? extends ValidResult> c) {
        success = false;
        return super.addAll(c);
    }

    @Override
    public boolean add(ValidResult result) {
        success = false;
        return super.add(result);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public void addResult(String field, String message) {
        this.add(new ValidResult(field, message));
    }

    public boolean isSuccess() {
        return success;
    }
}
