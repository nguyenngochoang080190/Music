package com.example.administrator.myapplicationdemo3.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.administrator.myapplicationdemo3.database.ManagerAllSongs;
import com.example.administrator.myapplicationdemo3.model.Song;
import com.example.administrator.myapplicationdemo3.util.PlayMusic;

import java.util.ArrayList;

/**
 * Created by Administrator on 9/22/2016.
 */
public class PlayMusicService extends Service{
    protected ArrayList<Song> mSongs;
    protected static PlayMusic mPlayMusic;
    protected int mPosition, currentPosition;
    protected Handler mHandler;
    protected Intent mIntent;
    private ManagerAllSongs mManagerAllSongs;
    protected boolean mIsPlay = false, mSendAll = true, mLoop = true, mLoopAll = true;
    public static String UPDATE_CURRENT_POSITON = "UPDATE_CURRENT_POSITON";
    public static String UPDATE_ALL_INFOMATION = "UPDATE_ALL_INFOMATION";
    public static String POSITION = "POSITION";
    public static String DURATION = "DURATION";
    public static String SONG_NAME = "SONG_NAME";
    public static String PLAY_MUSIC_SERVICE = "PLAY_MUSIC_SERVICE";
    boolean mIs_Live;
    private int time = 1000;

    @Override
    public void onCreate() {
        super.onCreate();
        mIs_Live = true;
        mPlayMusic = new PlayMusic(getApplicationContext());
        mIntent = new Intent();
        mIntent.setAction(PLAY_MUSIC_SERVICE);
        mManagerAllSongs = new ManagerAllSongs(getApplicationContext());
        mHandler = new Handler();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flag, int flag_1) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mHandler.removeCallbacks(UpdateControlMusic);
        mIs_Live = false;
        if (mPlayMusic.is_play()) {
            mPlayMusic.onStop();
            mPlayMusic.getmMediaPlayer().release();
        }
        super.onDestroy();

    }

    private Runnable UpdateControlMusic = new Runnable() {
        @Override
        public void run() {
            if (mIsPlay) {
                if (mSendAll == true) {

                    mSendAll = false;
                } else

                mHandler.postDelayed(this, time);
            }
        }
    };

}
