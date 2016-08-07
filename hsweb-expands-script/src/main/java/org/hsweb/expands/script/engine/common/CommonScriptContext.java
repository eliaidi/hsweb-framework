package org.hsweb.expands.script.engine.common;

import javax.script.CompiledScript;

public class CommonScriptContext extends org.hsweb.expands.script.engine.ScriptContext {
    private CompiledScript script;

    public CommonScriptContext(String id, String md5, CompiledScript script) {
        super(id, md5);
        this.script = script;
    }

    public CompiledScript getScript() {
        return script;
    }
}