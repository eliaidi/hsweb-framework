package org.hsweb.web.bean.valid;

/**
 * 单个属性验证结果
 */
public class ValidResult {
    public ValidResult() {
    }

    /**
     * 带参数构造方法，用于初始化验证的字段和验证的结果
     *
     * @param field   验证的字段
     * @param message 验证结果
     */
    public ValidResult(String field, String message) {
        this.field = field;
        this.message = message;
    }

    private String field;
    private String message;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("{\"%s\":\"%s\"}", getField(), getMessage());
    }
}