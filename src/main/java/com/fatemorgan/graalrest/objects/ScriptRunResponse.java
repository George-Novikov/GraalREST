package com.fatemorgan.graalrest.objects;

public class ScriptRunResponse {
    private Boolean result;
    private String message;

    public ScriptRunResponse() {}

    public ScriptRunResponse(Boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public ScriptRunResponse(Object result, Object message){
        setResult(result);
        setMessage(message);
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Object result) {
        if (result != null && result instanceof Boolean){
            this.result = (Boolean) result;
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        if (message != null && message instanceof String){
            this.message = (String) message;
        }
    }
}
