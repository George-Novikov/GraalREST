package com.fatemorgan.graalrest.tools;

import org.graalvm.polyglot.Value;
import org.springframework.util.MultiValueMap;

public class JsonBuilder {

    public String getJsonResult(MultiValueMap<String, Object> formInput, Value bindings){
        StringBuilder builder = new StringBuilder();
        builder.append("{");

        formInput.entrySet()
                .stream()
                .forEach(entry -> {
                    String key = entry.getKey();
                    Value argValue = bindings.getMember(key);
                    boolean isStringValue = argValue.isString();
                    String value = isStringValue ? argValue.asString(): argValue.as(argValue.getClass()).toString();

                    builder.append(
                            String.format(
                                    "%s%s%s,",
                                    quote(key),
                                    ": ",
                                    isStringValue ? quote(value) : value
                            )
                    );
                });

        builder.append("}");

        return builder.toString();
    }

    public String quote(String field){
        return String.format("\"%s\"", field);
    }
}
