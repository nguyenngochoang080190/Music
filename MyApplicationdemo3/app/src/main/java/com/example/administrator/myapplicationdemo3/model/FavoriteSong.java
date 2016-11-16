package com.example.administrator.myapplicationdemo3.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 9/17/2016.
 */
public class FavoriteSong extends Song implements Parcelable {
    public int ID_IN_ALL_SONGS;
    public int ID_IN_FAVORITE_ALUMS;
    public String FAVORITE_ALUMS;
    public FavoriteSong() {
        super();
    }

    protected FavoriteSong(Parcel in) {
        super(in);
        this.ID_IN_ALL_SONGS = in.readInt();
        this.ID_IN_FAVORITE_ALUMS = in.readInt();
        this.FAVORITE_ALUMS=in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(ID_IN_ALL_SONGS);
        dest.writeInt(ID_IN_FAVORITE_ALUMS);
        dest.writeString(FAVORITE_ALUMS);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FavoriteSong> CREATOR = new Creator<FavoriteSong>() {
        @Override
        public FavoriteSong createFromParcel(Parcel in) {
            return new FavoriteSong(in);
        }

        @Override
        public FavoriteSong[] newArray(int size) {
            return new FavoriteSong[size];
        }
    };

    public int getID_IN_ALL_SONGS() {
        return ID_IN_ALL_SONGS;
    }

    public void setID_IN_ALL_SONGS(int ID_IN_ALL_SONGS) {
        this.ID_IN_ALL_SONGS = ID_IN_ALL_SONGS;
    }

    public int getID_IN_FAVORITE_ALUMS() {
        return ID_IN_FAVORITE_ALUMS;
    }

    public void setID_IN_FAVORITE_ALUMS(int ID_IN_FAVORITE_ALUMS) {
        this.ID_IN_FAVORITE_ALUMS = ID_IN_FAVORITE_ALUMS;
    }

    public String getFAVORITE_ALUMS() {
        return FAVORITE_ALUMS;
    }

    public void setFAVORITE_ALUMS(String FAVORITE_ALUMS) {
        this.FAVORITE_ALUMS = FAVORITE_ALUMS;
    }
}
