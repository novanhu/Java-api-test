package com.ice.debug_test;

import com.ice.happypass.DTO.Result.TestResultDto;
import com.ice.happypass.common.BaseTest;
import com.ice.happypass.utilities.TimeRelated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.ice.happypass.common.BaseTest.testResults;


public class PrintInfoTest extends BaseTest {

    private long startTime;
    private String versionInfoApiEndpoint = null;

    private String cookie = null;
    private String userpGuid = null;

    long testStartTime;
    long testEndTime;
    TestResultDto singleTestResult;
    private String caseId = null;
    private  static  Logger logger = LoggerFactory.getLogger(PrintInfoTest.class);

    @BeforeClass()
    public void setup() throws InterruptedException {
        startTime = System.currentTimeMillis();
        Thread.sleep(1000*10);
    }

    @BeforeMethod
    private void initEach() {
        testStartTime = System.currentTimeMillis();
        singleTestResult = new TestResultDto();
    }

    @AfterMethod
    private void sendSingleTestResultToFinalTestResult() {
        testEndTime = System.currentTimeMillis();
        singleTestResult.setDuration((testEndTime - testStartTime) / 1000);
        singleTestResult.setVersion_tested("test_version");
        testResults.addTestResult(caseId, singleTestResult);
    }
    @AfterClass
    public void teardown() {
        TimeRelated.covertTime(System.currentTimeMillis() - startTime);
    }

    @Test
    public void testVersionInfoWithoutAuth4725113() {
        caseId = "case_1";
        logger.info("Test#1 *******************************");
        Assert.assertTrue(true);
        System.out.println("Hello World!");
        singleTestResult.addPassedTestStepResult("Step1 dadada");

        Assert.assertTrue(true);
        singleTestResult.addPassedTestStepResult("Step2 Verify the length of string.");

        Assert.assertTrue(true);
        singleTestResult.addPassedTestStepResult("Step3 200 status code returned.");
    }

    @Test
    public void testVersionInfoWithoutAuth3Failure() {
        caseId = "case_2";
        logger.info("Test#2 *******************************");
        System.out.println("Hello World, just test failures!");
        Assert.assertTrue(false,"Just test failure");
        singleTestResult.addFailedTestStepResult("Fail","fail");
    }
}
