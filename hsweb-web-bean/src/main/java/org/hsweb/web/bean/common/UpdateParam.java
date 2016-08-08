package org.hsweb.web.bean.common;

public class UpdateParam<T> extends org.hsweb.ezorm.param.UpdateParam<T, UpdateParam<T>> {

    public UpdateParam() {
    }

    public UpdateParam(T data) {
        set(data);
    }

    public static <T> UpdateParam<T> build(T data) {
        return new UpdateParam<>(data);
    }
}
