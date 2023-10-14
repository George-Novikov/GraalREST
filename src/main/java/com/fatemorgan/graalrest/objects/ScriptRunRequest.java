package com.fatemorgan.graalrest.objects;

import java.util.Map;

public class ScriptRunRequest {
    private String script;
    private Map<String, String> arguments;

    public ScriptRunRequest() {}

    public ScriptRunRequest(String script, Map<String, String> arguments) {
        this.script = script;
        this.arguments = arguments;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public Map<String, String> getArguments() {
        return arguments;
    }

    public void setArguments(Map<String, String> arguments) {
        this.arguments = arguments;
    }

    public boolean hasArguments(){
        return this.arguments != null && this.arguments.size() > 0;
    }
}
