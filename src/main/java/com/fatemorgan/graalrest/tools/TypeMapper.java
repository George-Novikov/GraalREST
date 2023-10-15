package com.fatemorgan.graalrest.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TypeMapper {

    private final List<Class> TYPES = Arrays.asList(
            Double.class,
            Boolean.class,
            Object.class,
            ArrayList.class,
            Double[].class,
            Boolean[].class,
            Object[].class,
            Integer.class,
            Integer[].class,
            String.class,
            String[].class
    );

    private ObjectMapper mapper;

    public TypeMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public Object convert(Object value){
        Object output = null;

        for (Class type : TYPES){
            try {
                output = mapper.readValue((String) value, type);
            } catch (JsonProcessingException jpe){
                continue;
            }
            if (output != null) break;
        }

        return output != null ? output : value;
    }
}
