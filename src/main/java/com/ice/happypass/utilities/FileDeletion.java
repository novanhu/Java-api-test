package com.ice.happypass.utilities;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/************************************************
 *    Delete files methods
 * *********************************************/

public class FileDeletion {

    private  static Logger logger = LoggerFactory.getLogger(FileDeletion.class);

    public static void deleteFile(String inputFile){
        File file = new File(inputFile);

        if(file.delete())
        {
            logger.info("File deleted successfully");
        }
        else
        {
            logger.info("Failed to delete the file");
        }
    }

}

