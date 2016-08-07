package org.hsweb.generator.swing.panel.support;

import org.hsweb.commons.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于KeyListener的快捷键支持
 * Created by 浩 on 2016-03-18 0018.
 */
public class ShortCutsAdapter extends KeyAdapter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //快捷键监听器
    private Map<String, ShortCutsListener> listenerMap = new ConcurrentHashMap<>();

    //当前按下了哪些按钮
    private Set<String> nowPressed = new LinkedHashSet<>();

    /**
     * 绑定一个快捷键，并传入快捷键监听器
     *
     * @param key      快捷键，不区分大小写。多个案件使用+链接，如：Ctrl+C
     * @param listener 监听器，按下快捷键时，触发监听器事件
     */
    public void bind(String key, ShortCutsListener listener) {
        key = key.toLowerCase();
        if (listener == null)
            listenerMap.remove(key);
        else
            listenerMap.put(key, listener);
    }

    /**
     * 执行一个快捷键监听器
     */
    protected void on(String key) {
        ShortCutsListener listener = listenerMap.get(key);
        logger.info(key);
        if (null != listener) {

            listener.press();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String text = KeyEvent.getKeyText(e.getKeyCode());
        if (text == null) return;
        nowPressed.add(text.toLowerCase());
        on(StringUtils.concatSpiltWith("+", nowPressed.toArray()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String text = KeyEvent.getKeyText(e.getKeyCode());
        if (text == null) return;
        nowPressed.remove(text.toLowerCase());
    }
}
