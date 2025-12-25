package com.ice.happypass.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

/************************************************
 *    Read all of content from one file to String
 * **********************************************/

public class FileToString {

    public static String convertFiletoString(String fileName){

        File file = new File(fileName);

        try {
            return new String(Files.readAllBytes(file.toPath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "not find file";
        } catch (IOException e) {
             e.printStackTrace();
             return "file read exception";
        }
    }

}
