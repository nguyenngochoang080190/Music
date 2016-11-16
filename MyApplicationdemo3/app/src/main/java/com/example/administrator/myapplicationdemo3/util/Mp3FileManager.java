package com.example.administrator.myapplicationdemo3.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.administrator.myapplicationdemo3.database.ManagerAllSongs;
import com.example.administrator.myapplicationdemo3.model.Song;

import java.util.ArrayList;

/**
 * Created by Administrator on 9/17/2016.
 */
public class Mp3FileManager {
    protected Context mContext;
    protected Uri uri= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    protected Cursor cursor;
    public static String MP3_TYPE=".mp3";
    public Mp3FileManager(Context context)
    {
        mContext=context;
    }
    public void getMp3FileCursor()
    {
        cursor=mContext.getContentResolver().query(uri,null,null,null,MediaStore.Audio.Media.DISPLAY_NAME+" ASC");
    }
    public ArrayList<Song> getArrayListMp3Song()
    {
        ArrayList<Song> songs=new ArrayList<>();
        getMp3FileCursor();
        if(cursor!=null)
        {
            Song song;
            String song_type;
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                song_type=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                if(song_type.endsWith(MP3_TYPE)||song_type.endsWith(MP3_TYPE.toLowerCase()))
                {
                    song= ManagerAllSongs.convertCursorToSong(cursor);
                    songs.add(song);
                }
                cursor.moveToNext();
            }
        }
        Log.v("HH","size "+songs.size());
        return songs;
    }
}
