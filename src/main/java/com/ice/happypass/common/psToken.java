package com.ice.happypass.common;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;


/******************************************
 *    Get psToken before do API testing
 * ***************************************/

public class psToken {

    private static Logger logger = LoggerFactory.getLogger(psToken.class);

    public static String getToken(String userName, String password,  String authEndpoint, String rURL)   throws JSONException {
        RestAssured.defaultParser = Parser.JSON;

        String request = new JSONObject().put("username", userName)
                .put("password", password)
                .put("rURL", rURL).toString();

        Response rp = given().body(request).when().with()
                .header("Content-Type", "application/json")
                .post(authEndpoint);

        return rp.path("psToken");

    }


     public static void destroySession(String psToken){
        RestAssured.defaultParser = Parser.JSON;

        Response rp = given().when().with()
                .given()
                .contentType("application/json")
                .header("Cookie", "psToken=" + psToken)
                .delete(ConstantDefinition.baseURI + "session");
        logger.info("Session destroy is done \n\r");
    }

      public static String getCookiefromToken(String psToken){

        String cookie;

        cookie = psToken;

        return cookie;
    }

}
