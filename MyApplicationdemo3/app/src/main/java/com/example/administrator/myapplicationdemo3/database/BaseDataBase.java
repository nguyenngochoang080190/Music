package com.example.administrator.myapplicationdemo3.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 9/17/2016.
 */
public class BaseDataBase extends DataBaseColumns {
     Context mContext;
    public static SQLiteDatabase mSqLiteDatabase;
    public static boolean IS_OPEN=false;
    public static int GET_ALL = -1;
    public static String DATABASE_NAME="manager_song";
    public static int DATABASE_VERSION=1;
    public static String[] DATABASE_COLUMNS = {
            ID, TITLE, TITLE_KEY, ARTIST, ARTIST_KEY, ALBUM, ALBUM_KEY, DATA, DISPLAY_NAME, DURATION,
            DATE_ADDED, DATE_MODIFIED, IS_ALARM, IS_MUSIC,
            IS_NOTIFICATION, IS_PODCAST, IS_RINGTONE, TRACK, YEAR, SIZE
    };
    public BaseDataBase(Context context)
    {
        mContext=context;
    }
    public void closeDatabase()
    {
        if(mSqLiteDatabase.isOpen())
        {
            mSqLiteDatabase.close();
            IS_OPEN=false;
        }
    }
}
