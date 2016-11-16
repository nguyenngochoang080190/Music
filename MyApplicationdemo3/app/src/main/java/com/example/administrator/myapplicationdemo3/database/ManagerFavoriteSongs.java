package com.example.administrator.myapplicationdemo3.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrator.myapplicationdemo3.model.Album;
import com.example.administrator.myapplicationdemo3.model.FavoriteSong;
import com.example.administrator.myapplicationdemo3.model.Song;
import com.example.administrator.myapplicationdemo3.util.CurrentTime;

import java.util.ArrayList;

/**
 * Created by Administrator on 9/17/2016.
 */
public class ManagerFavoriteSongs extends BaseDataBase {
    ManagerFavoriteSongsHelper mManagerFavoriteSongsHelper;
    public static String ID_IN_ALL_SONGS = "id_in_all_songs";
    public static String ID_IN_FAVORITE_ALUMS = "id_in_favorite_song";
    public static String TABLE_NAME = "manager_favorite_songs";
    public static String CREATE_TABLE = "create table "
            + TABLE_NAME + " ( "
            + ID + INTEGER_PRIMARY_KEY_TYPE + ","
            + ID_IN_ALL_SONGS + INTEGER_TYPE + ","
            + ID_IN_FAVORITE_ALUMS + INTEGER_TYPE + ","
            + DATE_ADDED + TEXT_TYPE + " ) ";
    public String[] ManagerFavoriteSongsColumns = {ID, ID_IN_ALL_SONGS, ID_IN_FAVORITE_ALUMS, DATE_ADDED};
    public static String DROP_TABLE = "drop table if exists " + TABLE_NAME;

    public ManagerFavoriteSongs(Context context) {
        super(context);
    }

    public ManagerFavoriteSongs openManagerFavoriteSongs() {
        mManagerFavoriteSongsHelper = new ManagerFavoriteSongsHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION);
        mSqLiteDatabase = mManagerFavoriteSongsHelper.getWritableDatabase();
        return this;
    }

    public void insertFavoriteSong(int id_in_all_songs, int id_in_album) {
        if (IS_OPEN) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ID_IN_ALL_SONGS, id_in_all_songs);
            contentValues.put(ID_IN_ALL_SONGS, id_in_album);
            contentValues.put(DATE_ADDED, CurrentTime.getTimeInMillis());
            mSqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        }
    }

    public ArrayList<FavoriteSong> getAllFavariteSongs(int album_id) {
        ArrayList<FavoriteSong> favoriteSongs;
        Cursor cursor;
        FavoriteSong favoriteSong;
        if (IS_OPEN) {
            favoriteSongs = new ArrayList<>();
            if (album_id == BaseDataBase.GET_ALL)
                cursor = mSqLiteDatabase.query(TABLE_NAME, ManagerFavoriteSongsColumns, null, null, null, null, null);
            else
                cursor = mSqLiteDatabase.query(TABLE_NAME, ManagerFavoriteSongsColumns, ID_IN_FAVORITE_ALUMS + "=?",
                        new String[]{String.valueOf(album_id)}, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                favoriteSong=new FavoriteSong();
                favoriteSong.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID))));
                favoriteSong.setID_IN_ALL_SONGS(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID_IN_ALL_SONGS))));
                favoriteSong.setID_IN_FAVORITE_ALUMS(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID_IN_FAVORITE_ALUMS))));
                favoriteSong.setDATE_ADDED(cursor.getString(cursor.getColumnIndex(DATE_ADDED)));
                ArrayList<Song> songs=new ManagerAllSongs(mContext).getArrayListSongs(favoriteSong.getID_IN_ALL_SONGS());
                Song song=songs.get(0);
                copy(song,favoriteSong);
                ArrayList<Album> albums=new ManagerFavoriteAlbums(mContext).getArrayListAlbums(favoriteSong.getID_IN_FAVORITE_ALUMS());
                favoriteSong.setFAVORITE_ALUMS(albums.get(0).getFAVORITE_ALBUM_NAME());
                favoriteSongs.add(favoriteSong);
                cursor.moveToNext();
            }
        } else
            return null;
        return favoriteSongs;
    }

    private void copy(Song song, FavoriteSong favoriteSong) {
        favoriteSong.setTITLE(song.getTITLE());
        favoriteSong.setTITLE_KEY(song.getTITLE_KEY());
        favoriteSong.setARTIST(song.getARTIST());
        favoriteSong.setARTIST_KEY(song.getARTIST_KEY());
        favoriteSong.setALBUM(song.getALBUM());
        favoriteSong.setALBUM_KEY(song.getALBUM_KEY());
        favoriteSong.setDATA(song.getDATA());
        favoriteSong.setDISPLAY_NAME(song.getDISPLAY_NAME());
        favoriteSong.setDURATION(song.getDURATION());
        favoriteSong.setIS_ALARM(song.getIS_ALARM());
        favoriteSong.setIS_MUSIC(song.getIS_MUSIC());
        favoriteSong.setIS_NOTIFICATION(song.getIS_NOTIFICATION());
        favoriteSong.setIS_PODCAST(song.getIS_PODCAST());
        favoriteSong.setIS_RINGTONE(song.getIS_RINGTONE());
        favoriteSong.setTRACK(song.getTRACK());
        favoriteSong.setYEAR(song.getYEAR());
        favoriteSong.setSIZE(song.getSIZE());
    }

    private class ManagerFavoriteSongsHelper extends SQLiteOpenHelper {

        public ManagerFavoriteSongsHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }
    }
}
