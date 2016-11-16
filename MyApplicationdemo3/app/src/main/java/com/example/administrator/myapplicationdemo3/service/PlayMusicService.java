package com.example.administrator.myapplicationdemo3.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.administrator.myapplicationdemo3.database.ManagerAllSongs;
import com.example.administrator.myapplicationdemo3.fragment.AllSongFragment;
import com.example.administrator.myapplicationdemo3.model.Song;
import com.example.administrator.myapplicationdemo3.util.PlayMusic;

import java.util.ArrayList;

/**
 * Created by Administrator on 9/22/2016.
 */
public class PlayMusicService extends Service implements PlayMusic.Listener{
    protected ArrayList<Song> mSongs;
    protected static PlayMusic mPlayMusic;
    protected int mPosition, currentPosition;
    public static String TAG=PlayMusicService.class.getSimpleName();
    protected Handler mHandler;
    protected Intent mIntent;
    private ManagerAllSongs mManagerAllSongs;
    public static boolean mIsPlay = false, mSendAll = true, mLoop = true, mLoopAll = true;
    public static String POSITION = "POSITION";
    public static String DURATION = "DURATION";
    public static String SONG_NAME = "SONG_NAME";
    boolean mIs_Live;
    private int time = 1000;

    @Override
    public void onCreate() {
        super.onCreate();
        mIs_Live = true;
        mPlayMusic = new PlayMusic(getApplicationContext());
        mPlayMusic.setOnComplete(this);
        mIntent = new Intent();
        mIntent.setAction(TAG);
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
        registerReceiver(mBroadcastReceiver,new IntentFilter(AllSongFragment.TAG));
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
                    mIntent.putExtra(SONG_NAME,mSongs.get(mPosition).getDISPLAY_NAME());
                    mIntent.putExtra(POSITION,mPlayMusic.getmMediaPlayer().getCurrentPosition());
                    mIntent.putExtra(DURATION,mPlayMusic.getmMediaPlayer().getDuration());
                } else
                {
                    mIntent.putExtra(POSITION,mPlayMusic.getmMediaPlayer().getCurrentPosition());
                }
                sendBroadcast(mIntent);
                mHandler.postDelayed(this, time);
            }
        }
    };
    BroadcastReceiver mBroadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(AllSongFragment.TAG))
            {
                mSendAll=true;
                mIsPlay=true;
                mPosition=intent.getIntExtra(AllSongFragment.POSITION,0);
                if(mSongs==null||mSongs.size()==0)
                {
                    mManagerAllSongs.openManagerAllSongs();
                    mSongs=mManagerAllSongs.getArrayListSongs(ManagerAllSongs.GET_ALL);
                    mManagerAllSongs.closeDatabase();
                }
                mPlayMusic.setData(mSongs.get(mPosition).getDATA());
                mPlayMusic.onPlay();
                UpdateControlMusic.run();
            }
        }
    };

    @Override
    public void finish() {
        if(mLoop)
        {
            mPlayMusic.setData(mSongs.get(mPosition).getDATA());
            mPlayMusic.onPlay();
        }
        else
            if(mLoopAll)
            {
                mPosition++;
                if(mPosition>=mSongs.size())
                    mPosition=0;
                mPlayMusic.setData(mSongs.get(mPosition).getDATA());
                mPlayMusic.onPlay();
            }
        else
                mPlayMusic.onStop();
    }
}
