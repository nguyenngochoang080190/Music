package com.example.administrator.myapplicationdemo3.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


import com.example.administrator.myapplicationdemo3.model.Album;

import java.util.ArrayList;

/**
 * Created by Administrator on 9/17/2016.
 */
public class ManagerFavoriteAlbums extends BaseDataBase {
    public static   ManagerFavoriteAlbumsHelper mManagerFavoriteAlbumsHelper;
    public static String FAVORITE_ALBUM_NAME = "favorite_album_name";
    public static String SIZE = "size";
    public static String DATABASE_NAME = "manager_favorite_albums";
    public static String TABLE_NAME = "favorite_albums";
    public static String DROP_TABLE = "drop table if exists " + TABLE_NAME;
    public static String CREATE_TABLE = "create table "
            + TABLE_NAME + " ( "
            + ID + INTEGER_PRIMARY_KEY_TYPE + ","
            + FAVORITE_ALBUM_NAME + TEXT_TYPE + ","
            + DATE_ADDED + TEXT_TYPE + ","
            + DATE_MODIFIED + TEXT_TYPE + ","
            + SIZE + INTEGER_TYPE + " )";
    public String[] ManagerFavoriteAlbumsColumns={ID,FAVORITE_ALBUM_NAME,DATE_ADDED,DATE_MODIFIED,SIZE};
    public ManagerFavoriteAlbums(Context context)
    {
        super(context);
    }
    public ManagerFavoriteAlbums openManagerFavoriteAlbums()
    {
        mManagerFavoriteAlbumsHelper=new ManagerFavoriteAlbumsHelper(mContext,DATABASE_NAME,null,DATABASE_VERSION);
        mSqLiteDatabase=mManagerFavoriteAlbumsHelper.getWritableDatabase();
        IS_OPEN=true;
        return this;
    }
    public void insertAlbum(Album album)
    {
       if(IS_OPEN)
       {
           ContentValues contentValues=new ContentValues();
           contentValues.put(FAVORITE_ALBUM_NAME,album.getFAVORITE_ALBUM_NAME());
           contentValues.put(DATE_ADDED,album.getDATE_ADDED());
           contentValues.put(DATE_MODIFIED,album.getDATE_MODIFIED());
           contentValues.put(SIZE,album.getSIZE());
           Log.v("HH1",""+contentValues.get(DATE_ADDED)+" "+contentValues.get(DATE_MODIFIED) +" "+contentValues.get(SIZE));
          mSqLiteDatabase.insert(TABLE_NAME,null,contentValues);
       }

    }
    public void  insertArrayListAlbums(ArrayList<Album> albums)
    {
        for (Album album:albums
             ) {
            insertAlbum(album);
        }
    }
    public ArrayList<Album> getArrayListAlbums(int id)
    {

        ArrayList<Album> albums=new ArrayList<>();
        Cursor cursor;
        Album album;
        if(IS_OPEN)
        {
            if(id==BaseDataBase.GET_ALL)
            {
                cursor=mSqLiteDatabase.query(TABLE_NAME,ManagerFavoriteAlbumsColumns,null,null,null,null,DATE_ADDED +" DESC");
            }
            else
                cursor=mSqLiteDatabase.query(TABLE_NAME,ManagerFavoriteAlbumsColumns,ID+"=?",new String[] {String.valueOf(id)},null,null,null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                album=new Album();
                album.setID(cursor.getInt(cursor.getColumnIndex(ID)));
                album.setFAVORITE_ALBUM_NAME(cursor.getString(cursor.getColumnIndex(FAVORITE_ALBUM_NAME)));
                Log.v("HH",cursor.getString(0)+"  "+ cursor.getString(1)+" "+cursor.getString(2)+"  "+cursor.getString(3)+"  "+cursor.getString(4)+"  ");
                album.setDATE_ADDED(cursor.getString(cursor.getColumnIndex(DATE_ADDED)));
                album.setDATE_MODIFIED(cursor.getString(cursor.getColumnIndex(DATE_MODIFIED)));
                album.setSIZE(cursor.getInt(cursor.getColumnIndex(SIZE)));
                albums.add(album);
                cursor.moveToNext();
            }

        }
        return  albums;
    }
    public int deleteAlbum(int album_id)
    {
        if(IS_OPEN)
            return mSqLiteDatabase.delete(TABLE_NAME,ID+"=?",new String[]{String.valueOf(album_id)});
        else
            return 0;
    }
    public class ManagerFavoriteAlbumsHelper extends SQLiteOpenHelper {
        public ManagerFavoriteAlbumsHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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
