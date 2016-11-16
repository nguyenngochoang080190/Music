package com.example.administrator.myapplicationdemo3.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.PowerManager;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Administrator on 9/22/2016.
 */
public class PlayMusic implements MediaPlayer.OnCompletionListener {
    String data;
    public Listener mListener;
    MediaPlayer mMediaPlayer;
    Context mContext;

    public PlayMusic(Context context) {
        mContext = context;
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setWakeMode(mContext, PowerManager.PARTIAL_WAKE_LOCK);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnCompletionListener(this);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(data);
            Log.v("H", data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mListener.finish();
    }

    public interface Listener {
        public void finish();
    }

    public void onPlay() {
        try {
            mMediaPlayer.prepare();
            mMediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onPause() {
        mMediaPlayer.pause();
    }
    public void onStop() {
        mMediaPlayer.stop();
    }

    public void onSeek(int msec) {
        mMediaPlayer.seekTo(msec);
    }

    public boolean is_play() {
        if (mMediaPlayer.isPlaying())
            return true;
        else
            return false;
    }
    public void setOnComplete(Listener listener){ this.mListener=listener;}

    public MediaPlayer getmMediaPlayer() {
        return mMediaPlayer;
    }

    public void setmMediaPlayer(MediaPlayer mMediaPlayer) {
        this.mMediaPlayer = mMediaPlayer;
    }
}
