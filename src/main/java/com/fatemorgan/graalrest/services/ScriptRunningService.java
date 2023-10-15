package com.fatemorgan.graalrest.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fatemorgan.graalrest.tools.JsonBuilder;
import com.fatemorgan.graalrest.tools.TypeMapper;
import org.graalvm.polyglot.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

@Service
public class ScriptRunningService {
    private Engine engine;
    private TypeMapper typeMapper;
    private JsonBuilder jsonBuilder;

    public ScriptRunningService(Engine engine,
                                TypeMapper typeMapper,
                                JsonBuilder jsonBuilder) {
        this.engine = engine;
        this.typeMapper = typeMapper;
        this.jsonBuilder = jsonBuilder;
    }

    public Object run(MultiValueMap<String, Object> formInput) throws JsonProcessingException {
        String script = (String) formInput.getFirst("script");
        if (script == null) throw new IllegalArgumentException("The 'script' parameter is absent.");

        try (Context context = buildContext()){
            Value bindings = context.getBindings("js");

            if (formInput.size() > 1){
                loadArguments(formInput, bindings);
            }

            context.eval(Source.create("js", script));

            return jsonBuilder.getJsonResult(formInput, bindings);
        }
    }

    private Context buildContext(){
        return Context
                .newBuilder("js")
                .allowHostAccess(HostAccess.ALL)
                .allowHostClassLookup(className -> true)
                .allowExperimentalOptions(true)
                .option("js.nashorn-compat", "true")
                .option("js.ecmascript-version", "6")
                .engine(engine)
                .build();
    }

    private void loadArguments(MultiValueMap<String, Object> formInput, Value bindings){
        formInput.remove("script");
        formInput.entrySet()
                .stream()
                .forEach(arg -> {
                    bindings.putMember(
                            arg.getKey(),
                            typeMapper.convert(
                                    arg.getValue().get(0)
                            )
                    );
                });
    }
}
