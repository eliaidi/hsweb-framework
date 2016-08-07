package org.hsweb.generator.logger;


import ch.qos.logback.core.OutputStreamAppender;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

/**
 * Created by 浩 on 2016-03-18 0018.
 */
public abstract class AbstractLoggerAppender<E> extends OutputStreamAppender<E> {

    private OutputStream proxy = new ByteArrayOutputStream() {
        @Override
        public synchronized void write(byte b[], int off, int len) {
            appendLogger(new String(b));
        }
    };

    public abstract void appendLogger(String log);


    @Override
    protected void append(E eventObject) {
        super.append(eventObject);
    }

    @Override
    public void start() {
        setOutputStream(proxy);
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }

}
