package com.demoblaze.utils;

import java.text.SimpleDateFormat;

public class TimeManager {

//screenshots - logs - reports
    public static String GetCurrentTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new java.util.Date());
    }

    public static String getSimpleTimestamp(){
        return  Long.toString(System.currentTimeMillis());
    }
}
