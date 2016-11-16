package com.example.administrator.myapplicationdemo3.util;

import java.util.Calendar;

/**
 * Created by Administrator on 9/17/2016.
 */
public class CurrentTime {
    protected static Calendar calendar=Calendar.getInstance();
    public static long getTimeInMillis()
    {
        return calendar.getTimeInMillis();
    }
}
