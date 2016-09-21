package com.example.ranalucky.retrofitexample.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by AshokSahu on 13/07/16.
 */
public class Utility {
    static Context mcontext;


    //Get App Version Name
    public static int getVersionName(Context context){
        int versionCode= 0;
        try {
            versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return versionCode;
    }



    public static String getTimeDifference(Date fromTime,Date toTime){
        String result="";
        Date d1 = fromTime;
        Date d2 = toTime;

        try {
            //d1 = format.parse(dateStart);
            //d2 = format.parse(dateStop);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

           // System.out.print(diffDays + " days, ");
            //System.out.print(diffHours + " hours, ");
            //System.out.print(diffMinutes + " minutes, ");
            //System.out.print(diffSeconds + " seconds.");
            result = diffHours+":"+diffMinutes;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String addZeroToTheEndWithNonZeroNumber(String timeVal)

    {


        String timehours = timeVal;
        Log.e("timehours", timehours);

        if(timehours.equals("0")==true){
            timehours="0"+timehours;
            //Log.e("timehours", timehours);
        }
        if(timehours.equals("1")==true){
            timehours="0"+timehours;
            //Log.e("timehours", timehours);
        }

        if(timehours.equals("2")==true){
            timehours="0"+timehours;
            Log.e("value ::", timehours);
        }

        if(timehours.equals("3")==true){
            timehours="0"+timehours;
        }

        if(timehours.equals("4")==true){
            timehours="0"+timehours;
        }

        if(timehours.equals("5")==true){
            timehours="0"+timehours;
        }
        if(timehours.equals("6")==true){
            timehours="0"+timehours;
        }

        if(timehours.equals("7")==true){
            timehours="0"+timehours;
        }

        if(timehours.equals("8")==true){
            timehours="0"+timehours;
        }

        if(timehours.equals("9")==true){
            timehours="0"+timehours;
        }
        return timehours;
    }


    public static String getCorrectedDate(String date) {

        String dateString;
        String[] splitTime = date.split("-");

        String yearString = splitTime[0];
        String monthString = getMonthName(splitTime[1]);
        String dayString = splitTime[2];
        dateString = dayString+" "+monthString+" "+yearString;
        return dateString;


    }

public static String getMonthName(String value)
{
    String monthName = "";
   if(value.equals("1") || value.equals("01"))
    {
        monthName = "Jan" ;

    }
    else if(value.equals("2") || value.equals("02"))
    {
        monthName = "Feb" ;

    }
    else if(value.equals("3") || value.equals("03"))
    {
        monthName = "Mar" ;

    }
    else if(value.equals("4") || value.equals("04"))
    {
        monthName = "Apr" ;

    }
    else if(value.equals("5") || value.equals("05"))
    {
        monthName = "May" ;

    }
    else if(value.equals("6") || value.equals("06"))
    {
        monthName = "June" ;

    }
    else if(value.equals("7") || value.equals("07"))
    {
        monthName = "July" ;

    }
    else if(value.equals("8") || value.equals("08"))
    {
        monthName = "Aug" ;

    }
    else if(value.equals("9") || value.equals("09"))
    {
        monthName = "Sep" ;

    }
    else if(value.equals("10") )
    {
        monthName = "Oct" ;

    }
    else if(value.equals("11") )
    {
        monthName = "Nov" ;

    }else if(value.equals("12"))
    {
        monthName = "Dec" ;

    }



    return monthName;
}
    public static String SeperateHourMinSec(String timeVal) {
        String[] splitTime = timeVal.split("T");
        String timeString = splitTime[1];

        String timeValue = "";
        String[] split_seconds = timeString.split(":");
        String timehours = split_seconds[0];
        String timeMin = split_seconds[1];
        String timSec = split_seconds[2];

        String finalTime = timehours+":"+timeMin;
        //  Log.e("timehours", timehours);

        /*if (timehours.equals("0") == true) {
            timehours = "0" + timehours;
            //Log.e("timehours", timehours);
        *///}
        return finalTime;

    }

    public static String SeperateTime(String timeVal)
    {


        String timeValue="";
        String[] split_seconds = timeVal.split("-");
        String timehours = split_seconds[0];
        //  Log.e("timehours", timehours);

        if(timehours.equals("0")==true){
            timehours="0"+timehours;
            //Log.e("timehours", timehours);
        }
        if(timehours.equals("1")==true){
            timehours="0"+timehours;
            //Log.e("timehours", timehours);
        }

        if(timehours.equals("2")==true){
            timehours="0"+timehours;
        }

        if(timehours.equals("3")==true){
            timehours="0"+timehours;
        }

        if(timehours.equals("4")==true){
            timehours="0"+timehours;
        }

        if(timehours.equals("5")==true){
            timehours="0"+timehours;
        }
        if(timehours.equals("6")==true){
            timehours="0"+timehours;
        }

        if(timehours.equals("7")==true){
            timehours="0"+timehours;
        }

        if(timehours.equals("8")==true){
            timehours="0"+timehours;
        }

        if(timehours.equals("9")==true){
            timehours="0"+timehours;
        }
        String timeminutes = split_seconds[1];

        if(timeminutes.equals("0")==true){
            timeminutes="0"+timeminutes;
            //Log.e("timehours", timeminutes);
        }
        if(timeminutes.equals("1")==true){
            timeminutes="0"+timeminutes;
            //Log.e("timehours", timeminutes);
        }

        if(timeminutes.equals("2")==true){
            timeminutes="0"+timeminutes;
        }

        if(timeminutes.equals("3")==true){
            timeminutes="0"+timeminutes;
        }

        if(timeminutes.equals("4")==true){
            timeminutes="0"+timeminutes;
        }

        if(timeminutes.equals("5")==true){
            timeminutes="0"+timeminutes;
        }
        if(timeminutes.equals("6")==true){
            timeminutes="0"+timeminutes;
        }

        if(timeminutes.equals("7")==true){
            timeminutes="0"+timehours;
        }

        if(timeminutes.equals("8")==true){
            timeminutes="0"+timeminutes;
        }

        if(timeminutes.equals("9")==true){
            timeminutes="0"+timeminutes;
        }

        //  String[] split_sec = seconds.split("");
        String seconds_value = split_seconds[2];
        Log.e("**************", "seconds value is"+seconds_value);

        if (seconds_value.equals("*0")) {



            timeValue = timehours+"-"+timeminutes+"am";

        }
        else if(seconds_value.equals("*1"))
        {
            timeValue = timehours+"-"+timeminutes+"pm";
        }



        return timeValue;

    }




    public static String getMonthNameStr(int monthNum){
        String result = "";
        String[] str = {"JAN",
                "FEB",
                "MAR",
                "APR",
                "MAY",
                "JUN",
                "JUL",
                "AUG",
                "SEP",
                "OCT",
                "NOV",
                "DEC"};
        if(monthNum<=str.length)
            result = str[monthNum-1];
        else
            result = "Invalid month";
        return result;
    }


    public static String getWeekDayStr(int dayNum){
        String result = "";
        String[] str = {"Sun",
                "Mon",
                "Tue",
                "Wed",
                "Thu",
                "Fri",
                "Sat"};
        if(dayNum<=str.length)
            result = str[dayNum-1];
        else
            result = "Invalid Day";
        return result;
    }

    public static String calculateEta1(String journeyTimeMin){
        String newTime = "-";
        try {
            if(journeyTimeMin!=null && !journeyTimeMin.equals("-")) {
                journeyTimeMin = journeyTimeMin.replace(" mins", "");
                SimpleDateFormat df = new SimpleDateFormat("hh:mm a");
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MINUTE, Integer.parseInt(journeyTimeMin));
                newTime = df.format(cal.getTime());
            }else
            {
                 newTime = "NA";
            }
        }
        catch(NumberFormatException e) {
        }
        return newTime;
    }

    public static String calculateEtaEx(long journeyTimeSec){
        String newTime = "-";
        try {
            SimpleDateFormat df = new SimpleDateFormat("hh:mm a");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.SECOND, (int)journeyTimeSec);
            newTime = df.format(cal.getTime());
        }
        catch(NumberFormatException e) {
        }
        return newTime;
    }

    /**
     * Don't use this method...this is only for demo purpose..
     */

	
    public static String checkNullString(String value){
        String result = "";
        if(value!=null) {
            if (!value.equals("null")) {
                result = value;
            }
        }
        return result;    }


    public static boolean isLocationReliable( long locTimeStamp) {
        boolean gpsIsOld = false;

        long old = System.currentTimeMillis() - 60000;
        System.out.print("System.currentTimeMillis()  "+System.currentTimeMillis());
        System.out.print("old  Time  "+old);

        gpsIsOld = (locTimeStamp < old);
        System.out.print("gpsIsOld  "+gpsIsOld);



        return gpsIsOld;
    }








    public static boolean isLocationReliable2(Context context , long locTimeStamp) {
        boolean gpsIsOld = false;

        long old = System.currentTimeMillis() - getGPSCheckMilliSecsFromPrefs(context);

         gpsIsOld = (locTimeStamp < old);

        if(!gpsIsOld) {
            updateGpsTimeStamp(context , locTimeStamp);
        }

        return gpsIsOld;
    }




    public static void  updateGpsTimeStamp(Context context ,long value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constants.MY_PREFS_NAME,context.MODE_PRIVATE).edit();
        editor.putLong("lastGpsUpdate", value);
        editor.commit();
    }

    public static  long getGPSCheckMilliSecsFromPrefs(Context context) {

        long gpsLastUpdate = 0;
        SharedPreferences prefs = context.getSharedPreferences(Constants.MY_PREFS_NAME,context.MODE_PRIVATE);
        gpsLastUpdate = prefs.getLong("lastGpsUpdate",gpsLastUpdate);


        return gpsLastUpdate;

    }




    public static int removeDots(String distance) {
        int dis = 0;
        String[] split_seconds =  distance.split(".");
        String value = split_seconds[1];
        dis = Integer.parseInt(value);



        return dis;
    }

    public static String getDayValue(String s) {

        String[] split_seconds =  s.split("-");
        //String month = split_seconds[1];
        String day = split_seconds[2];

       return day;
    }

    public static String getCorrectedMonthName(String s) {

        String[] split_seconds =  s.split("-");
        //String month = split_seconds[1];
        String month = split_seconds[1];

        return month;
    }

    public static String getYear(String s) {

        String[] split_seconds =  s.split("-");
        //String month = split_seconds[1];
        String year = split_seconds[0];

        return year;
    }

    public static String getDayName(String s) {

        // Assuming that you already have this.
        int year = Integer.parseInt(getYear(s));
        int month = Integer.parseInt(getCorrectedMonthName(s));
        int day = Integer.parseInt(getDayValue(s));

// First convert to Date. This is one of the many ways.
        String dateString = String.format("%d-%d-%d", year, month, day);
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-M-d").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

// Then get the day of week from the Date based on specific locale.
        String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);

        System.out.println(dayOfWeek);

        String dayName = dayOfWeek.substring(0, Math.min(3, dayOfWeek.length()));

        return dayName;
    }
}
