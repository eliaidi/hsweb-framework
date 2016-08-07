package org.hsweb.generator.exception;

/**
 * Created by 浩 on 2016-03-20 0020.
 */
public class MethodNotSupportException extends RuntimeException {
    public MethodNotSupportException(String message) {
        super(message);
    }

    public MethodNotSupportException(String message, Throwable cause) {
        super(message, cause);
    }
}
