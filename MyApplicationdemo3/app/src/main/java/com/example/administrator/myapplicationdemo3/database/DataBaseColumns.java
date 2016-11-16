package com.example.administrator.myapplicationdemo3.database;

import android.provider.MediaStore;

/**
 * Created by Administrator on 9/17/2016.
 */
public class DataBaseColumns extends TypeColumns {
    //20 COLUMNS
    public static String ID = "_id";
    public static String TITLE = MediaStore.Audio.Media.TITLE;
    public static String TITLE_KEY = MediaStore.Audio.Media.TITLE_KEY;
    public static String ARTIST = MediaStore.Audio.Media.ARTIST;
    public static String ARTIST_KEY = MediaStore.Audio.Media.ARTIST_KEY;
    public static String ALBUM = MediaStore.Audio.Media.ALBUM;
    public static String ALBUM_KEY = MediaStore.Audio.Media.ALBUM_KEY;
    public static String DATA = MediaStore.Audio.Media.DATA;
    public static String DISPLAY_NAME = MediaStore.Audio.Media.DISPLAY_NAME;
    public static String DURATION = MediaStore.Audio.Media.DURATION;
    public static String DATE_ADDED = MediaStore.Audio.Media.DATE_ADDED;
    public static String DATE_MODIFIED = MediaStore.Audio.Media.DATE_MODIFIED;
    public static String IS_ALARM = MediaStore.Audio.Media.IS_ALARM;
    public static String IS_MUSIC = MediaStore.Audio.Media.IS_MUSIC;
    public static String IS_NOTIFICATION = MediaStore.Audio.Media.IS_NOTIFICATION;
    public static String IS_PODCAST = MediaStore.Audio.Media.IS_PODCAST;
    public static String IS_RINGTONE = MediaStore.Audio.Media.IS_RINGTONE;
    public static String TRACK = MediaStore.Audio.Media.TRACK;
    public static String YEAR = MediaStore.Audio.Media.YEAR;
    public static String SIZE = MediaStore.Audio.Media.SIZE;

}
