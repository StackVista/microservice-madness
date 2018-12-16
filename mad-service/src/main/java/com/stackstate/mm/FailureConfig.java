package com.stackstate.mm;

public class FailureConfig {

    private String service;
    private String failureMode;
    private String failureModeParam;

    public FailureConfig() {}
    public FailureConfig(String service, String failureMode, String failureModeParam) {
        this.service = service;
        this.failureMode = failureMode;
        this.failureModeParam = failureModeParam;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getFailureMode() {
        return failureMode;
    }

    public void setFailureMode(String failureMode) {
        this.failureMode = failureMode;
    }

    public boolean isFailureMode(String wantedMode) {
        return failureMode == null ? false : failureMode.equalsIgnoreCase(wantedMode);
    }

    public String getFailureModeParam() {
        return failureModeParam;
    }

    public Double getFailureModeParamAsDouble() {
        return failureModeParam == null ? null : new Double(failureModeParam);
    }

    public void setFailureModeParam(String failureModeParam) {
        this.failureModeParam = failureModeParam;
    }
}
