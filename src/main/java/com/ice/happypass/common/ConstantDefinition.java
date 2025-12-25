package com.ice.happypass.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.util.Properties;

/******************************************
 *    Read variables from properties file
 * ***************************************/

public class ConstantDefinition {

    public static String userName;
    public static String password;

    public static String baseURI;
    public static String apiVersion;

    public static Properties props = null;
    public static Properties props2 = null;
    public static Properties props3 = null;
    public static String rURL = null;

    public static String cacheEndpoint;

    private static Logger logger = LoggerFactory.getLogger(ConstantDefinition.class);

    public static void getConstant(String environment) {
        props = new Properties();
        props2 = new Properties();
        props3 = new Properties();
        FileInputStream fis = null;
        FileInputStream fis_Different = null;
        Boolean isLoadDifferentProperties = false;
        try {
            logger.info("get basic env properties...");
            if (environment == null) {
                logger.info("If the environment is empty, get the E1 environment property...");
                fis = new FileInputStream("src/main/resources/dev_env_common.properties");
                fis_Different = new FileInputStream("src/main/resources/dev_env_different.properties");
                isLoadDifferentProperties = true;
            } else {
                if (environment.equalsIgnoreCase("preprod")) {
                    logger.info("get prod properties...");
                    fis = new FileInputStream("src/main/resources/prod.properties");
                }else {
                    logger.info("get " + environment + " env properties...");
                    fis = new FileInputStream("src/main/resources/dev_env_common.properties");
                    fis_Different = new FileInputStream("src/main/resources/dev_env_different.properties");
                    isLoadDifferentProperties = true;
                }
            }
            props.load(fis);
            fis.close();
            if (isLoadDifferentProperties) {
                props2.load(fis_Different);
                fis_Different.close();
            }
            if (environment == null) {
                baseURI = props2.getProperty("baseURI").replace("domain", "E1" + ".dev");
            } else {
                    baseURI = props2.getProperty("baseURI").replace("domain", environment + ".dev");
            }

            userName = props.getProperty("private.domain.user1.name");
            password = props.getProperty("private.domain.user1.password");
            rURL = baseURI;

            apiVersion = props.getProperty("apiVersion");


            cacheEndpoint = props.getProperty("cacheEndpoint");

        } catch (Exception e) {
            throw new RuntimeException("Can't get properties file", e);
        }
    }
}
