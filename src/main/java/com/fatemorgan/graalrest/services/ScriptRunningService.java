package com.fatemorgan.graalrest.services;

import com.fatemorgan.graalrest.objects.ScriptRunRequest;
import com.fatemorgan.graalrest.objects.ScriptRunResponse;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Map;

@Service
public class ScriptRunningService {
    public Object run(ScriptRunRequest script) throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("graal.js");
        Bindings bindings = engine.createBindings();
        bindings.put("result", "true");
        bindings.put("message", "OK");
        if (script.hasArguments()) bindings.putAll(script.getArguments());
        Object result = engine.eval(script.getScript(), bindings);
        return new ScriptRunResponse(engine.get("result"), engine.get("message"));
    }
}
