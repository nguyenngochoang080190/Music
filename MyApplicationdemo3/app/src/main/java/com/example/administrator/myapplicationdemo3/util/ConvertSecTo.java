package com.example.administrator.myapplicationdemo3.util;

/**
 * Created by Hoang-Admin on 8/18/2016.
 */
public class ConvertSecTo {
    public static String convert(long time)
    {
        int giay=(int)time/1000;
        String s="";
        int hour, minute,second;
        hour=(int)giay/3600;
        minute=(int)(giay/60-hour*60);
        second=(int) (giay-minute*60-hour*3600);
        if(hour<10&&hour>0)
            s+="0"+hour;
        else
        if(hour>=10)
            s+=hour+":";
        if(minute<10)
            s+="0"+minute+":";
        else
            s+=minute+":";
        if(second<10)
            s+="0"+second;
        else
            s+=second;
        return s;
    }
}
