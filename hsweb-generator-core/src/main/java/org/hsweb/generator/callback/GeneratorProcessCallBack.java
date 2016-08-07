package org.hsweb.generator.callback;

/**
 * 生成器执行进程回调
 */
public interface GeneratorProcessCallBack {
    void process(int step, String message);
}
