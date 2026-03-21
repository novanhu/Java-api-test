package com.ice.debug_test;

import com.ice.happypass.DTO.Result.TestResultDto;
import com.ice.happypass.common.BaseTest;
import com.ice.happypass.utilities.TimeRelated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
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
    SoftAssert softAssert = new SoftAssert();

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
        String actual1 = "实际结果";
        String actual2 = "正确";
        softAssert.assertEquals(actual1, "test", "验证1失败");
        softAssert.assertEquals(actual2, "test", "验证2失败");
        softAssert.assertTrue(false, "验证3失败");

        boolean finalResult = true;
        try {
            softAssert.assertAll();
        } catch (AssertionError e) {
            finalResult = false;
        }
        System.out.println("最终结果: " + finalResult);
        if(finalResult){
            singleTestResult.addPassedTestStepResult("Happy test result");
        }else {
            singleTestResult.addFailedTestStepResult("step1 do some test","actual result didn't match with expected result");
        }
        Assert.assertTrue(finalResult);
    }
}
