package org.hsweb.expands.script.engine;

public interface ScriptListener {
    void before(ScriptContext context);

    void after(ScriptContext context, ExecuteResult result);
}
