package com.ice.happypass.utilities;

import au.com.bytecode.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/******************************************
 *    For file reading and writing
 * ***************************************/
public class FileRelated {

    private  static Logger logger = LoggerFactory.getLogger(FileRelated.class);

    public static int getCsvFileRowsCount(String fileName) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(fileName));
        List<String[]> linesList = new ArrayList<>();
        String[] line;
        while ((line = reader.readNext())!=null){
            linesList.add(line);
        }
        return linesList.size();
    }

    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getCsvFileLinesCount(String fileName) {
        FileReader in = null;
        try {
            in = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        LineNumberReader reader = new LineNumberReader(in);
        try {
            reader.skip(Long.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int lines = reader.getLineNumber();
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
