package com.example.administrator.myapplicationdemo3.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 9/16/2016.
 */
public class Song extends BaseSong implements Parcelable {
    public int ID;
    public Song() {
        super();
    }

    public void songPacel(Parcel in) {
        this.ID = in.readInt();
        this.TITLE = in.readString();
        this.TITLE_KEY = in.readString();
        this.ARTIST = in.readString();
        this.ARTIST_KEY = in.readString();
        this.ALBUM = in.readString();
        this.ALBUM_KEY = in.readString();
        this.DATA = in.readString();
        this.DISPLAY_NAME = in.readString();
        this.DURATION = in.readString();
        this.DATE_ADDED = in.readString();
        this.DATE_MODIFIED = in.readString();
        this.IS_ALARM = in.readString();
        this.IS_MUSIC = in.readString();
        this.IS_NOTIFICATION = in.readString();
        this.IS_PODCAST = in.readString();
        this.IS_RINGTONE = in.readString();
        this.TRACK = in.readString();
        this.YEAR = in.readString();
        this.SIZE = in.readString();
    }

    protected Song(Parcel in) {
        songPacel(in);
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public void songWriteToParcel(Parcel dest) {
        dest.writeInt(ID);
        dest.writeString(TITLE);
        dest.writeString(TITLE_KEY);
        dest.writeString(ARTIST);
        dest.writeString(ARTIST_KEY);
        dest.writeString(ALBUM);
        dest.writeString(ALBUM_KEY);
        dest.writeString(DATA);
        dest.writeString(DISPLAY_NAME);
        dest.writeString(DURATION);
        dest.writeString(DATE_ADDED);
        dest.writeString(DATE_MODIFIED);
        dest.writeString(IS_ALARM);
        dest.writeString(IS_MUSIC);
        dest.writeString(IS_NOTIFICATION);
        dest.writeString(IS_PODCAST);
        dest.writeString(IS_RINGTONE);
        dest.writeString(TRACK);
        dest.writeString(YEAR);
        dest.writeString(SIZE);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        songWriteToParcel(dest);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String toString() {
        return "ID: " + ID + super.toString();
    }
}
