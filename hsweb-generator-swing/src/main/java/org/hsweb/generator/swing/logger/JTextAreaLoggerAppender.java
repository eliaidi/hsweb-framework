package org.hsweb.generator.swing.logger;

import org.hsweb.generator.logger.AbstractLoggerAppender;

import javax.swing.*;

/**
 * Created by 浩 on 2016-03-18 0018.
 */
public class JTextAreaLoggerAppender<E> extends AbstractLoggerAppender<E> {

    private static JTextArea textArea;

    @Override
    public void appendLogger(String log) {
        if (textArea != null) {
            synchronized (JTextAreaLoggerAppender.class) {
                textArea.append(log);
                int length = textArea.getText().length();
                textArea.setCaretPosition(length);
            }
        }
    }

    public static void bindTextArea(JTextArea textArea) {
        synchronized (JTextAreaLoggerAppender.class) {
            JTextAreaLoggerAppender.textArea = textArea;
        }
    }
}
