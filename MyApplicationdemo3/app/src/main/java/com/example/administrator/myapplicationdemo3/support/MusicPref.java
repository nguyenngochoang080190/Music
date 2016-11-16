package com.example.administrator.myapplicationdemo3.support;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 13/11/2016.
 */

public class MusicPref {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static Context context;
    public static String PREF = "music_pref";
    public static String DATABASE = "database";
    public static String ID = "id";
    public static String SEEK_TIME = "seek_time";
    public static String REPEAT = "repeat";
    public static int REPEAT_ONE = 1;
    public static int REPEAT_ALL = 2;
    public static int NO_REPEAT = 0;

    public MusicPref(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public void set_music(int id, long seek_time) {
        editor.putInt(ID, id);
        editor.putLong(SEEK_TIME, seek_time);
        editor.commit();

    }

    public void set_database(boolean b) {
        editor.putBoolean(DATABASE, b);
        editor.commit();
    }

    public void set_repeat(int t) {
        editor.putInt(REPEAT, t);
        editor.commit();
    }

    public int get_id() {
        return sharedPreferences.getInt(ID, -1);
    }

    public long get_seektime() {
        return sharedPreferences.getLong(SEEK_TIME, 0);
    }

    public boolean get_database() {
        return sharedPreferences.getBoolean(DATABASE, false);
    }

    public int get_repeat() {
        return sharedPreferences.getInt(REPEAT, 0);
    }
}
