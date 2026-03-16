package com.ice.happypass.DTO.Result;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class TestStepDetailsDto implements Serializable {
    public static String PASSED = "PASSED";
    public static String FAILED = "FAILED";
    @JsonProperty("step_status")
    private String stepStatus;
    @JsonProperty("step_result")
    private String stepResult;
    @JsonProperty("step_error")
    private String stepError;

    public void setStepStatus(String stepStatus) {
        this.stepStatus = stepStatus;
    }

    public String getStepStatus() {
        return this.stepStatus;
    }

    public void setStepResult(String stepResult) {
        this.stepResult = stepResult;
    }

    public String getStepResult() {
        return this.stepResult;
    }

    public void setStepError(String stepError) {
        this.stepError = stepError;
    }

    public String getStepError() {
        return this.stepError;
    }
}
