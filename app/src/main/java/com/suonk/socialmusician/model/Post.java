package com.suonk.socialmusician.model;

import java.util.Date;

public class Post {
    private String id;
    private String uid;
    private String contentText;
    private String contentImg;
    private String contentVideo;
    private String contentAudio;
    private Date date;

    public Post(String contentText, String contentImg, String contentVideo, String contentAudio, Date date) {
        this.contentText = contentText;
        this.contentImg = contentImg;
        this.contentVideo = contentVideo;
        this.contentAudio = contentAudio;
        this.date = date;
    }

    public String getuid() {
        return uid;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getContentImg() {
        return contentImg;
    }

    public void setContentImg(String contentImg) {
        this.contentImg = contentImg;
    }

    public String getContentVideo() {
        return contentVideo;
    }

    public void setContentVideo(String contentVideo) {
        this.contentVideo = contentVideo;
    }

    public String getContentAudio() {
        return contentAudio;
    }

    public void setContentAudio(String contentAudio) {
        this.contentAudio = contentAudio;
    }

    public Date getDate() {
        return date;
    }
}
