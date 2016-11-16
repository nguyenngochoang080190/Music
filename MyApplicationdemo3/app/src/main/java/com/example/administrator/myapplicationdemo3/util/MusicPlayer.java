package com.example.administrator.myapplicationdemo3.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.PowerManager;

import java.io.IOException;

/**
 * Created by Hoang-Admin on 8/17/2016.
 */
public class MusicPlayer implements MediaPlayer.OnCompletionListener  {
    MediaPlayer mediaPlayer;
    Context mContext;
    Listener mListener;
    public MusicPlayer(Context context) {
        mContext = context;
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setWakeMode(mContext, PowerManager.PARTIAL_WAKE_LOCK);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnCompletionListener(this);

    }

    public void setData(String path) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Stop() {
        mediaPlayer.stop();
    }
    public void Pause()
    {
        mediaPlayer.pause();
    }
    public void Seek(int t)
    {
        mediaPlayer.seekTo(t);
    }
    public void Play() {

        try {

            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean isPlaying() {
        if (mediaPlayer.isPlaying())
            return true;
        else
            return false;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mListener.sendResult(true);
    }
    public interface Listener
    {
        public void sendResult(boolean b);
    }
    public void setOnCompleteListener(Listener listener)
    {
        this.mListener=listener;
    }
}
