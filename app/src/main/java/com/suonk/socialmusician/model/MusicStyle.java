package com.suonk.socialmusician.model;

public class MusicStyle {
    private int idMusicStyle;
    private String musicStyle;

    public MusicStyle(String musicStyle) {
        this.musicStyle = musicStyle;
    }

    public String getMusicStyle() {
        return musicStyle;
    }
}