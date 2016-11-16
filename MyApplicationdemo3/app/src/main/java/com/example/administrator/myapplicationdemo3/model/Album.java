package com.example.administrator.myapplicationdemo3.model;

/**
 * Created by Administrator on 9/17/2016.
 */
public class Album {
    public  int ID;
    public  String FAVORITE_ALBUM_NAME;
    public  String DATE_ADDED;
    public  String DATE_MODIFIED;
    public  int SIZE;
    public Album(){}
    public Album(String name, int size ){
        FAVORITE_ALBUM_NAME=name;
        SIZE=size;
    }
    public String getFAVORITE_ALBUM_NAME() {
        return FAVORITE_ALBUM_NAME;
    }

    public void setFAVORITE_ALBUM_NAME(String FAVORITE_ALBUM_NAME) {
        this.FAVORITE_ALBUM_NAME = FAVORITE_ALBUM_NAME;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSIZE() {
        return SIZE;
    }

    public void setSIZE(int SIZE) {
        this.SIZE = SIZE;
    }

    public String getDATE_MODIFIED() {
        return DATE_MODIFIED;
    }

    public void setDATE_MODIFIED(String DATE_MODIFIED) {
        this.DATE_MODIFIED = DATE_MODIFIED;
    }

    public String getDATE_ADDED() {
        return DATE_ADDED;
    }

    public void setDATE_ADDED(String DATE_ADDED) {
        this.DATE_ADDED = DATE_ADDED;
    }
}
