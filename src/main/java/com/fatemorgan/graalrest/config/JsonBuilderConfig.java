package com.fatemorgan.graalrest.config;

import com.fatemorgan.graalrest.tools.JsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonBuilderConfig {
    @Bean
    public JsonBuilder jsonBuilder(){
        return new JsonBuilder();
    }
}
