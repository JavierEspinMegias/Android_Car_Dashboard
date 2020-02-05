package com.android.myapplication;

import android.media.MediaPlayer;

import java.io.Serializable;

public class CarMediaPlayer extends MediaPlayer implements Serializable {
    MediaPlayer instMe = new MediaPlayer();

    public CarMediaPlayer(){
        instMe = new MediaPlayer();

    }

    public CarMediaPlayer(MediaPlayer instMe) {
        this.instMe = instMe;
    }

    public MediaPlayer getInstMe() {
        return instMe;
    }

    public void setInstMe(MediaPlayer instMe) {
        this.instMe = instMe;
    }

}