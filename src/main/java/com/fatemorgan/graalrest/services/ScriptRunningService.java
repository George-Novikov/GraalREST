package com.fatemorgan.graalrest.services;

import com.fatemorgan.graalrest.objects.ScriptRunRequest;
import com.fatemorgan.graalrest.objects.ScriptRunResponse;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

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

    public ScriptRunResponse run(MultiValueMap<String, String> formInput) {
        String script = formInput.getFirst("script");
        if (script == null) throw new IllegalArgumentException("'script' parameter is absent.");

        bindings.putMember("result", true);
        bindings.putMember("message", "OK");

        if (formInput.size() > 1){
            formInput.remove("script");
            formInput.entrySet()
                    .stream()
                    .forEach(arg -> bindings.putMember(
                            arg.getKey(),
                            arg.getValue().get(0)
                    ));
        }

        context.enter();
        context.eval(Source.create("js", script));

        ScriptRunResponse response = new ScriptRunResponse(
                bindings.getMember("result"),
                bindings.getMember("message")
        );

        context.leave();
        return response;
    }
}
