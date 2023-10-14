package com.fatemorgan.graalrest.config;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.graalvm.polyglot.HostAccess;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

@Configuration
public class ScriptRunnerConfig {
    @Bean
    public ScriptEngine scriptEngine(){
        return new ScriptEngineManager().getEngineByName("graal.js");
    }

    @Bean
    public Engine engine(){
        return Engine
                .newBuilder()
                .option("engine.WarnInterpreterOnly", "false")
                .build();
    }

    @Bean
    public Context context(){
        return Context
                .newBuilder("js")
                .allowHostAccess(HostAccess.ALL)
                .allowHostClassLookup(className -> true)
                .allowExperimentalOptions(true)
                .option("js.nashorn-compat", "true")
                .option("js.ecmascript-version", "6")
                .engine(engine())
                .build();
    }
}
