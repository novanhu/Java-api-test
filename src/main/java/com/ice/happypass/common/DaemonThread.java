package com.ice.happypass.common;

import com.ice.happypass.utilities.TimeRelated;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.ice.happypass.common.BaseTest.testDone;

public class DaemonThread implements Runnable{
    String endpoint;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void start(String environment) {
        if (environment!= null && environment.equalsIgnoreCase("prod")) {
            endpoint = "https://platform.prod.com/";
        } else {
            endpoint = "https://platform.Dev.com/";
        }
        Thread setUserStatusThread = new Thread(this);
        setUserStatusThread.start();
    }
    @Override
    public synchronized void run() {
        while(!testDone){
            List<CompletableFuture<Void>> completableFutures = new ArrayList<>();
            TimeRelated.doDelay(3000*60);
            List<String> tokens = new ArrayList<>();
            logger.info("Send heart beat to platform ......");
            tokens.forEach(token -> {
                CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
                   Response rp =  RestAssured.
                            given()
                            .header("Authorization", token)
                            .contentType("application/json")
                            .queryParam("clientId", "clientId")
                            .when()
                            .put(endpoint);
                logger.info("The response statusCode for alive user is: " + rp.getStatusCode());
                    return null;
                });
                completableFutures.add(future);
                logger.info("Send user " + (tokens.indexOf(token)+1) +  " alive message to Platform Services");
            });
            completableFutures.forEach(CompletableFuture::join);
        }

        System.out.println("==== TEST IS DONE ====");
    }
}
