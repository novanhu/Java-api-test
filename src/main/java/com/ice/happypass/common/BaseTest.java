package com.ice.happypass.common;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.ice.happypass.utilities.TimeRelated;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.sql.Connection;
import java.sql.Statement;

/******************************************
 * Testing setup
 ***************************************/
public class BaseTest {

    private long startTime = 0;
    private static Connection jupiterDBConn = null;
    private static Statement jupiterDBStmt = null;
    private static Statement ioDBStmt = null;
    private static String authToken = null;
    private static int userId = 0;
    private static String userpGuid = null;
    private static String cookie;



    public static String env;

    public static boolean bNonFunctionTest = false;

    private static DaemonThread t;

    public static Boolean testDone = false;

    private static Logger logger = LoggerFactory.getLogger(BaseTest.class);


    @Parameters({"environment"})
    @BeforeSuite
    public void init(@Optional String environment) throws JSONException {
        logger.info("Come here to start testing ...");
        logger.info("The time to start testing is " + TimeRelated.getCurrentTimeStamp());
        startTime = System.currentTimeMillis();
        ConstantDefinition.getConstant(environment);
        env = environment;

        t = new DaemonThread();
        t.start(environment);

    }

    public static Connection getJupiterDBConn() {
        return jupiterDBConn;
    }

    public static Statement getJupiterDBStmt() {
        return jupiterDBStmt;
    }

    public static Statement getIoDBStmt() {
        return ioDBStmt;
    }

    public static String getAuthToken() {
        return authToken;
    }

    public static String getCookie() {
        return cookie;
    }

    public static int getUserId() {
        return userId;
    }

    public static String getUserpGuid() {
        return userpGuid;
    }



    public static String getAdminUserpGuid() {
        return "";
    }


    public static String getEnvName() {
        return env;
    }

    @AfterSuite
    public void destroy() throws JSONException, JsonProcessingException {
        logger.info("Data clean up is done before testing end");


        if (!bNonFunctionTest) {
            logger.info("current test is functional test, so destroy API super user account token");
        } else {
            logger.info("current test is not functional test, so not destroy API super user account token");
        }
        logger.info("Destroy token before testing end");
        TimeRelated.covertTime(System.currentTimeMillis() - startTime);

        logger.info("The time to end testing is " + TimeRelated.getCurrentTimeStamp().toString());

        testDone = true;

        TimeRelated.doDelay(3000);

    }


}
