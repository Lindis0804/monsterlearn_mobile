package com.ldh.monsterlearn.Model;

public class Song {
    private int picture;
    private String name;
    private int res;

    public Song(int picture, String name, int res) {
        this.picture = picture;
        this.name = name;
        this.res = res;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }
}
