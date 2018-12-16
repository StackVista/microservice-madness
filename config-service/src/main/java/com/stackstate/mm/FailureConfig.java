package com.stackstate.mm;

public class FailureConfig {

    private final String service;
    private final String failureMode;
    private final String failureModeParam;

    public FailureConfig(String service, String failureMode, String failureModeParam) {
        this.service = service;
        this.failureMode = failureMode;
        this.failureModeParam = failureModeParam;
    }

    public String getService() {
        return service;
    }

    public String getFailureMode() {
        return failureMode;
    }

    public String getFailureModeParam() {
        return failureModeParam;
    }

    public String toString() {
      return "FailureConfig: mode " + failureMode + " and param " + failureModeParam;
    }
}
