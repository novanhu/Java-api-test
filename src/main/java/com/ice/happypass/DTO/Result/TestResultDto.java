package com.ice.happypass.DTO.Result;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestResultDto implements Serializable {
    @JsonProperty("step_result_list")
    private List<TestStepDetailsDto> stepResultList = new ArrayList();
    @JsonProperty("actual_results")
    private String actualResults;
    @JsonProperty("duration")
    private long duration;
    @JsonProperty("version_tested")
    private String version_tested;

    public void addPassedTestStepResult(String stepResult) {
        this.addTestStepResult(TestStepDetailsDto.PASSED, stepResult);
    }

    public void addFailedTestStepResult(String stepResult, String stepError) {
        this.addTestStepResult(TestStepDetailsDto.FAILED, stepResult, stepError);
    }

    public void addTestStepResult(String stepStatus, String stepResult) {
        TestStepDetailsDto testStepDetails = this.setStatusAndResult(stepStatus, stepResult);
        this.stepResultList.add(testStepDetails);
    }

    public void addTestStepResult(String stepStatus, String stepResult, String stepError) {
        TestStepDetailsDto testStepDetails = this.setStatusAndResult(stepStatus, stepResult);
        testStepDetails.setStepError(stepError);
        this.stepResultList.add(testStepDetails);
    }

    public TestStepDetailsDto setStatusAndResult(String stepStatus, String stepResult) {
        TestStepDetailsDto testStepDetails = new TestStepDetailsDto();
        testStepDetails.setStepStatus(stepStatus);
        testStepDetails.setStepResult(stepResult);
        testStepDetails.setStepError("");
        return testStepDetails;
    }

    public void setDuration(long duration) {
        this.duration = duration == 0L ? 1L : duration;
    }

    public List<TestStepDetailsDto> getStepResultList() {
        return this.stepResultList;
    }

    public String getActualResults() {
        return this.actualResults;
    }

    public String getVersion_tested() {
        return this.version_tested;
    }

    @JsonProperty("step_result_list")
    public void setStepResultList(List<TestStepDetailsDto> stepResultList) {
        this.stepResultList = stepResultList;
    }

    @JsonProperty("actual_results")
    public void setActualResults(String actualResults) {
        this.actualResults = actualResults;
    }

    @JsonProperty("version_tested")
    public void setVersion_tested(String version_tested) {
        this.version_tested = version_tested;
    }

    public long getDuration() {
        return this.duration;
    }
}
