package com.ice.debug_test;

import com.ice.happypass.utilities.TimeRelated;
import org.testng.Assert;
import org.testng.annotations.*;


public class PrintInfoTest {

    private long startTime;
    private String versionInfoApiEndpoint = null;

    private String cookie = null;
    private String userpGuid = null;

    long testStartTime;
    long testEndTime;
    @BeforeClass()
    public void setup() throws InterruptedException {
        startTime = System.currentTimeMillis();
        Thread.sleep(1000*10);
    }

    @BeforeMethod
    private void initEach() {
        testStartTime = System.currentTimeMillis();
    }

    @AfterMethod
    private void sendSingleTestResultToFinalTestResult() {
        testEndTime = System.currentTimeMillis();
    }
    @AfterClass
    public void teardown() {
        TimeRelated.covertTime(System.currentTimeMillis() - startTime);
    }

    @Test
    public void testVersionInfoWithoutAuth4725113() {
        Assert.assertTrue(true);
        System.out.println("Hello World!");
    }
}
