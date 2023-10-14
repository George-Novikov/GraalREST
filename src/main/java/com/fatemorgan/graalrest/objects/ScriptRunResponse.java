package com.fatemorgan.graalrest.objects;

import org.graalvm.polyglot.Value;

public class ScriptRunResponse {
    private Boolean result;
    private String message;

    public ScriptRunResponse() {}

    public ScriptRunResponse(Boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public ScriptRunResponse(Value result, Value message){
        setResult(result);
        setMessage(message);
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Value result) {
        if (result != null && result.isBoolean()){
            this.result = result.asBoolean();
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(Value message) {
        if (message != null && message.isString()){
            this.message = message.asString();
        }
    }
}
