package com.fatemorgan.graalrest.config;

import org.graalvm.polyglot.Engine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScriptRunnerConfig {
    @Bean
    public Engine engine(){
        return Engine
                .newBuilder()
                .option("engine.WarnInterpreterOnly", "false")
                .build();
    }
}
