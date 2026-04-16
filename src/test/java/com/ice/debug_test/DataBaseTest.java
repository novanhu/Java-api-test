package com.ice.debug_test;

import com.ice.happypass.DTO.Result.TestResultDto;
import com.ice.happypass.common.BaseTest;
import com.ice.happypass.common.ConstantDefinition;
import com.ice.happypass.utilities.TimeRelated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBaseTest extends BaseTest {

    private long startTime;
    private String versionInfoApiEndpoint = null;

    private String cookie = null;
    private String userpGuid = null;

    long testStartTime;
    long testEndTime;
    TestResultDto singleTestResult;
    private String caseId = null;
    private  static  Logger logger = LoggerFactory.getLogger(DataBaseTest.class);
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
    public void testDataBaseConnection() {
        String url = ConstantDefinition.databaseUrl;
        String user = ConstantDefinition.databaseUserName;
        String password = ConstantDefinition.databaseUserPassword;

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String aa = conn.getMetaData().getDatabaseProductVersion();
            System.out.println("✅ 连接成功！数据库版本：" + conn.getMetaData().getDatabaseProductVersion());
        } catch (SQLException e) {
            System.out.println("连接失败：" + e.getMessage());
        }
    }


}
