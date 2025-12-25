package com.ice.happypass.utc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/******************************************************************
 *    UTC testing methods
 * ***************************************************************/
public class Utc {

    private  static Logger logger = LoggerFactory.getLogger(Utc.class);

    public static String UTC_TIME_IN_JSON = "\"2016-01-28T15:15:05+00:00\"";
    public static String UTC_TIME_ID_DB = "2016-01-28 15:15:05";

    public static String ANY_DATE_IN_JSON = "\"2016-01-28\"";
    public static String ANY_DATE = "2016-01-28";

    public static String getCurrentDate(){
        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
        //sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String utcDate = sdf.format(currentTime);

        return utcDate;
    }
    public static String getCurrentUTCDate(){

        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String utcTime = sdf.format(currentTime);
        logger.info("UTC time: " + sdf.format(currentTime));

        return utcTime;
    }

    public static String getCurrentUTC(){

        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String utcTime = sdf.format(currentTime);
        logger.info("UTC time: " + sdf.format(currentTime));

        return utcTime;
    }
    public static String getFullCurrentUTC(){
        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String utcTime = sdf.format(currentTime);
        logger.info("UTC time: " + sdf.format(currentTime));

        return utcTime;
    }

    public static String getAnyDateUTC(String anyDate) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate;
        String utcTime = null;

        try{
            parsedDate = dateFormat.parse(anyDate);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            utcTime = sdf.format(parsedDate);
        }catch(ParseException pe){
            logger.info("Parse date failed.");
        }
        return utcTime;
    }

    public static boolean isValidUTCDate(String str) {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }

    public static boolean isValidUTCDateFormatBySetTime(String str) {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }

}
