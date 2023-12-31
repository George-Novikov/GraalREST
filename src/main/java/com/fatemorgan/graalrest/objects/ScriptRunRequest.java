package com.fatemorgan.graalrest.objects;

import java.util.Map;

/* Can be used to transfer data via JSON, but the script must be minified */
public class ScriptRunRequest {
    private String script;
    private Map<String, Object> arguments;

    public ScriptRunRequest() {}

    public ScriptRunRequest(String script, Map<String, Object> arguments) {
        this.script = script;
        this.arguments = arguments;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public Map<String, Object> getArguments() {
        return arguments;
    }

    public void setArguments(Map<String, Object> arguments) {
        this.arguments = arguments;
    }

    public boolean hasArguments(){
        return this.arguments != null && this.arguments.size() > 0;
    }
}
