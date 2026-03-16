package com.ice.happypass.DTO.Result;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestResults extends ConcurrentHashMap<String, TestResultDto> {
    private  static  Logger LOGGER = LoggerFactory.getLogger(TestResults.class);
    public void addTestResult(String testCaseId, TestResultDto singleTestResult) {
        if (testCaseId != null) {
            this.put(testCaseId, singleTestResult);
        }

    }

    public void writeToJsonFile(String fileName) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String dataToWrite = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        try {
            FileWriter fileWriter = new FileWriter(fileName, false);
            fileWriter.write(dataToWrite);
            fileWriter.flush();
        } catch (IOException e) {
            LOGGER.error("IOException occurred", e);
        } catch (Error e) {
            LOGGER.error("Error occurred", e);
        }

    }
}

