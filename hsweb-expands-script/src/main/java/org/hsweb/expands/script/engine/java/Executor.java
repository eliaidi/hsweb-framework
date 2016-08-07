package org.hsweb.expands.script.engine.java;

import java.util.Map;

public interface Executor {

    Object execute(Map<String, Object> var) throws Exception;
}
