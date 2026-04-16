package com.ice.debug_test;

import com.ice.happypass.DTO.OtherDTO.User;
import com.ice.happypass.DTO.Result.TestResultDto;
import com.ice.happypass.common.BaseTest;
import com.ice.happypass.common.ConstantDefinition;
import com.ice.happypass.utilities.TimeRelated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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
    public void testDataBaseConnection() throws SQLException {
        String url = ConstantDefinition.databaseUrl;
        String user = ConstantDefinition.databaseUserName;
        String password = ConstantDefinition.databaseUserPassword;

        Connection conn = DriverManager.getConnection(url, user, password);
        String aa = conn.getMetaData().getDatabaseProductVersion();
        logger.info("数据库连接成功！数据库版本：" + conn.getMetaData().getDatabaseProductVersion());
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users");

        System.out.println("✅ 连接成功！");
        System.out.println("========================================");

        // 获取列信息（用于打印表头）
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        // 打印列名（表头）
        for (int i = 1; i <= columnCount; i++) {
            System.out.printf("%-15s", metaData.getColumnName(i));
        }
        System.out.println();
        System.out.println("========================================");
        List userList = new ArrayList();
        User u=null;
        // 遍历结果集，打印每一行数据
        int rowCount = 0;
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-15s", rs.getString(i));
                u = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("city"),
                        rs.getString("email")
                );

            }
            userList.add(u);
            System.out.println();
            rowCount++;
        }

        System.out.println("========================================");
        System.out.println("共查询到 " + rowCount + " 条数据");

    }


}
