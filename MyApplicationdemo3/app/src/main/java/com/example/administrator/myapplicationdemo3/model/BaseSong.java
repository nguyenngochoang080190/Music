package com.example.administrator.myapplicationdemo3.model;

/**
 * Created by Administrator on 9/17/2016.
 */
public class BaseSong {
    //19 COLUMNS
    public String TITLE;
    public String TITLE_KEY;
    public String ARTIST;
    public String ARTIST_KEY;
    public String ALBUM;
    public String ALBUM_KEY;
    public String DATA;
    public String DISPLAY_NAME;
    public String DURATION;
    public String DATE_ADDED;
    public String DATE_MODIFIED;
    public String IS_ALARM;
    public String IS_MUSIC;
    public String IS_NOTIFICATION;
    public String IS_PODCAST;
    public String IS_RINGTONE;
    public String TRACK;
    public String YEAR;
    public String SIZE;

    public BaseSong() {
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getTITLE_KEY() {
        return TITLE_KEY;
    }

    public void setTITLE_KEY(String TITLE_KEY) {
        this.TITLE_KEY = TITLE_KEY;
    }

    public String getARTIST() {
        return ARTIST;
    }

    public void setARTIST(String ARTIST) {
        this.ARTIST = ARTIST;
    }

    public String getARTIST_KEY() {
        return ARTIST_KEY;
    }

    public void setARTIST_KEY(String ARTIST_KEY) {
        this.ARTIST_KEY = ARTIST_KEY;
    }

    public String getALBUM() {
        return ALBUM;
    }

    public void setALBUM(String ALBUM) {
        this.ALBUM = ALBUM;
    }

    public String getALBUM_KEY() {
        return ALBUM_KEY;
    }

    public void setALBUM_KEY(String ALBUM_KEY) {
        this.ALBUM_KEY = ALBUM_KEY;
    }

    public String getDATA() {
        return DATA;
    }

    public void setDATA(String DATA) {
        this.DATA = DATA;
    }

    public String getDISPLAY_NAME() {
        return DISPLAY_NAME;
    }

    public void setDISPLAY_NAME(String DISPLAY_NAME) {
        this.DISPLAY_NAME = DISPLAY_NAME;
    }

    public String getDURATION() {
        return DURATION;
    }

    public void setDURATION(String DURATION) {
        this.DURATION = DURATION;
    }

    public String getDATE_ADDED() {
        return DATE_ADDED;
    }

    public void setDATE_ADDED(String DATE_ADDED) {
        this.DATE_ADDED = DATE_ADDED;
    }

    public String getDATE_MODIFIED() {
        return DATE_MODIFIED;
    }

    public void setDATE_MODIFIED(String DATE_MODIFIED) {
        this.DATE_MODIFIED = DATE_MODIFIED;
    }

    public String getIS_ALARM() {
        return IS_ALARM;
    }

    public void setIS_ALARM(String IS_ALARM) {
        this.IS_ALARM = IS_ALARM;
    }

    public String getIS_MUSIC() {
        return IS_MUSIC;
    }

    public void setIS_MUSIC(String IS_MUSIC) {
        this.IS_MUSIC = IS_MUSIC;
    }

    public String getIS_NOTIFICATION() {
        return IS_NOTIFICATION;
    }

    public void setIS_NOTIFICATION(String IS_NOTIFICATION) {
        this.IS_NOTIFICATION = IS_NOTIFICATION;
    }

    public String getIS_PODCAST() {
        return IS_PODCAST;
    }

    public void setIS_PODCAST(String IS_PODCAST) {
        this.IS_PODCAST = IS_PODCAST;
    }

    public String getIS_RINGTONE() {
        return IS_RINGTONE;
    }

    public void setIS_RINGTONE(String IS_RINGTONE) {
        this.IS_RINGTONE = IS_RINGTONE;
    }

    public String getTRACK() {
        return TRACK;
    }

    public void setTRACK(String TRACK) {
        this.TRACK = TRACK;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }

    public String getSIZE() {
        return SIZE;
    }

    public void setSIZE(String SIZE) {
        this.SIZE = SIZE;
    }
    public String toString() {
        return "TIT: " + TITLE + "- ARTIST: " + ARTIST
                + "- ALBUM: " + ALBUM + "- DATA: " + DATA+ "- DURATION: " + DURATION
                + "- DATE_ADDED: " + DATE_ADDED+ "- DATE_MODIFIED: " + DATE_MODIFIED+ "- IS_ALARM: " + IS_ALARM
                + "- IS_RINGTONE: " + IS_RINGTONE+ "- IS_NOTI: " + IS_NOTIFICATION+ "- IS_POD: " + IS_PODCAST
                + "- IS_MUSIC: " + IS_MUSIC+ "- TRACK: " + TRACK+ "- YEAR: " + YEAR
                + "- SIZE: " + SIZE+"\n";
    }
}
