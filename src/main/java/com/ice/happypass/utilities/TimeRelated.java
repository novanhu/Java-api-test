package com.ice.happypass.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
/******************************************
 *    Get timestamp and print test duration
 * ***************************************/
public class TimeRelated {

    private  static Logger logger = LoggerFactory.getLogger(TimeRelated.class);

    public static java.sql.Timestamp getCurrentTimeStamp(){
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimeStamp = new java.sql.Timestamp(now.getTime());

        return currentTimeStamp;
    }

    public static void covertTime(long milliSeconds){

        int seconds = (int) (milliSeconds / 1000) % 60 ;
        int minutes = (int) ((milliSeconds / (1000*60)) % 60);
        int hours   = (int) ((milliSeconds / (1000*60*60)) % 24);

        logger.info("Testing duration " + hours + " Hour(s) " + minutes + " Minute(s) "+ seconds + " Second(s) " + "\n\r");
    }

    public static void doDelay(long delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ie) {
            logger.info("Can't delay");
        }
    }
}
