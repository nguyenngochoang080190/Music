package com.example.administrator.myapplicationdemo3.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.administrator.myapplicationdemo3.model.Song;
import com.example.administrator.myapplicationdemo3.util.Mp3FileManager;

import java.util.ArrayList;

/**
 * Created by Administrator on 9/17/2016.
 */
public class ManagerAllSongs extends BaseDataBase {
    private static ManagerAllSongs mManagerAllSongs;
    ManagerAllSongHelper mManagerAllSongHelper;
    public static String TABLE_NAME = "all_songs";//=
    public static String DROP_TABLE = "drop table if exists " + TABLE_NAME;
    public static String CREATE_TABLE = "create table "
            + TABLE_NAME + " ( "
            + ID + INTEGER_PRIMARY_KEY_TYPE + ","
            + TITLE + TEXT_TYPE + ","
            + TITLE_KEY + TEXT_TYPE + ","
            + ARTIST + TEXT_TYPE + ","
            + ARTIST_KEY + TEXT_TYPE + ","
            + ALBUM + TEXT_TYPE + ","
            + ALBUM_KEY + TEXT_TYPE + ","
            + DATA + TEXT_TYPE + ","
            + DISPLAY_NAME + TEXT_TYPE + ","
            + DURATION + INTEGER_TYPE + ","
            + DATE_ADDED + TEXT_TYPE + ","
            + DATE_MODIFIED + TEXT_TYPE + ","
            + IS_ALARM + INTEGER_TYPE + ","
            + IS_MUSIC + INTEGER_TYPE + ","
            + IS_NOTIFICATION + INTEGER_TYPE + ","
            + IS_PODCAST + INTEGER_TYPE + ","
            + IS_RINGTONE + INTEGER_TYPE + ","
            + TRACK + TEXT_TYPE + ","
            + YEAR + INTEGER_TYPE + ","
            + SIZE + INTEGER_TYPE
            + " )";

    public ManagerAllSongs(Context context) {
        super(context);
    }

    public ManagerAllSongs openManagerAllSongs() {
        try {
            mManagerAllSongHelper = new ManagerAllSongHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION);
            mSqLiteDatabase = mManagerAllSongHelper.getWritableDatabase();
            IS_OPEN = true;
        } catch (Exception e) {
            IS_OPEN = false;
        }

        return this;
    }

    public void insertSong(Song song) {
        if (IS_OPEN) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(TITLE, song.getTITLE());
            contentValues.put(TITLE_KEY, song.getTITLE_KEY());
            contentValues.put(ARTIST, song.getARTIST());
            contentValues.put(ARTIST_KEY, song.getARTIST_KEY());
            contentValues.put(ALBUM, song.getALBUM());
            contentValues.put(ALBUM_KEY, song.getALBUM_KEY());
            contentValues.put(DATA, song.getDATA());
            contentValues.put(DISPLAY_NAME, song.getDISPLAY_NAME());
            contentValues.put(DURATION, song.getDURATION());
            contentValues.put(DATE_ADDED, song.getDATE_ADDED());
            contentValues.put(DATE_MODIFIED, song.getDATE_MODIFIED());
            contentValues.put(IS_ALARM, song.getIS_ALARM());
            contentValues.put(IS_MUSIC, song.getIS_MUSIC());
            contentValues.put(IS_NOTIFICATION, song.getIS_NOTIFICATION());
            contentValues.put(IS_PODCAST, song.getIS_PODCAST());
            contentValues.put(IS_RINGTONE, song.getIS_RINGTONE());
            contentValues.put(TRACK, song.getTRACK());
            contentValues.put(YEAR, song.getYEAR());
            contentValues.put(SIZE, song.getSIZE());
            mSqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        }

    }

    public void insertArrayListSong(ArrayList<Song> songs) {
        for (Song song : songs
                ) {
            insertSong(song);
        }
    }

    public ArrayList<Song> getArrayListSongs(int selection) {
        ArrayList<Song> songs;
        Cursor cursor;
        Song song;
        if (IS_OPEN) {
            songs = new ArrayList<>();
            if (selection == BaseDataBase.GET_ALL)
                cursor = mSqLiteDatabase.query(TABLE_NAME, DATABASE_COLUMNS, null, null, null, null, null);
            else
                cursor = mSqLiteDatabase.query(TABLE_NAME, DATABASE_COLUMNS, ID + "=?", new String[]{String.valueOf(selection)}, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                songs.add(convertCursorToSong(cursor));
                cursor.moveToNext();
            }
        } else
            return null;
        return songs;
    }

    public int deleteSong(int song_id) {
        if (IS_OPEN) {
            return mSqLiteDatabase.delete(TABLE_NAME, ID + "=?", new String[]{String.valueOf(song_id)});
        } else
            return 0;
    }

    public static Song convertCursorToSong(Cursor cursor) {
        Song song = new Song();
        song.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID))));
        song.setTITLE(cursor.getString(cursor.getColumnIndex(TITLE)));
        song.setTITLE_KEY(cursor.getString(cursor.getColumnIndex(TITLE_KEY)));
        song.setARTIST(cursor.getString(cursor.getColumnIndex(ARTIST)));
        song.setARTIST_KEY(cursor.getString(cursor.getColumnIndex(ARTIST_KEY)));
        song.setALBUM(cursor.getString(cursor.getColumnIndex(ALBUM)));
        song.setALBUM_KEY(cursor.getString(cursor.getColumnIndex(ALBUM_KEY)));
        song.setDATA(cursor.getString(cursor.getColumnIndex(DATA)));
        song.setDISPLAY_NAME(cursor.getString(cursor.getColumnIndex(DISPLAY_NAME)));
        song.setDURATION(cursor.getString(cursor.getColumnIndex(DURATION)));
        song.setDATE_ADDED(cursor.getString(cursor.getColumnIndex(DATE_ADDED)));
        song.setDATE_MODIFIED(cursor.getString(cursor.getColumnIndex(DATE_MODIFIED)));
        song.setIS_ALARM(cursor.getString(cursor.getColumnIndex(IS_ALARM)));
        song.setIS_MUSIC(cursor.getString(cursor.getColumnIndex(IS_MUSIC)));
        song.setIS_NOTIFICATION(cursor.getString(cursor.getColumnIndex(IS_NOTIFICATION)));
        song.setIS_PODCAST(cursor.getString(cursor.getColumnIndex(IS_PODCAST)));
        song.setIS_RINGTONE(cursor.getString(cursor.getColumnIndex(IS_RINGTONE)));
        song.setTRACK(cursor.getString(cursor.getColumnIndex(TRACK)));
        song.setYEAR(cursor.getString(cursor.getColumnIndex(YEAR)));
        song.setSIZE(cursor.getString(cursor.getColumnIndex(SIZE)));
        return song;

    }

    public void deleteAllSongs() {
        mManagerAllSongHelper.onUpgrade(mSqLiteDatabase, DATABASE_VERSION, DATABASE_VERSION);
    }

    private class ManagerAllSongHelper extends SQLiteOpenHelper {

        public ManagerAllSongHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
//            Log.v("DATABSE: ",CREATE_TABLE);
            db.execSQL(CREATE_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }
    }
    public static void onUpdateDatabase(Context context)
    {
        mManagerAllSongs=new ManagerAllSongs(context);
        mManagerAllSongs.openManagerAllSongs();
        Mp3FileManager mp3FileManager=new Mp3FileManager(context);
        mManagerAllSongs.insertArrayListSong(mp3FileManager.getArrayListMp3Song());
        mManagerAllSongs.closeDatabase();
    }
}
