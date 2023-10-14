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
    Engine engine;
    Context context;
    Value bindings;

    public ScriptRunningService(Engine engine, Context context, Value bindings) {
        this.engine = engine;
        this.context = context;
        this.bindings = bindings;
    }

    public Object run(ScriptRunRequest script) {
        bindings.putMember("result", true);
        bindings.putMember("message", "OK");

        if (script.hasArguments()){
            script.getArguments().entrySet()
                    .stream()
                    .forEach(arg -> bindings.putMember(
                            arg.getKey(),
                            arg.getValue()
                    ));
        }

        Value function = context.eval(Source.create("js", script.getScript()));

        return new ScriptRunResponse(
                bindings.getMember("result"),
                bindings.getMember("message")
        );
    }
}
