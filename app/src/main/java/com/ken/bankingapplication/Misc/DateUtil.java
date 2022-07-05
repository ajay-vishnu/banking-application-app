package com.ken.bankingapplication.Misc;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static Date toDate(String dateAsString) {  //The method takes a string object and converts it to date
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return df.parse(dateAsString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    public static String toString(Date date)    {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy, HH:mm:ss");
        return df.format(date);
    }

}
