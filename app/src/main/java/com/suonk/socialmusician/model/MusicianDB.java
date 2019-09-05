package com.suonk.socialmusician.model;

public class MusicianDB {

    private int id;
    private String name;
    private String localisation;
    private String description;
    private String instrument;
    private MusicStyle musicStyle;
    private int profileImage;
    private int age;
    private boolean isFriend;

    public MusicianDB(int id, String name, String localisation, String description, int age, int profileImage, String instrument, MusicStyle musicStyle, Boolean isFriend) {
        this.id = id;
        this.name = name;
        this.localisation = localisation;
        this.description = description;
        this.instrument = instrument;
//        this.musicStyle = musicStyle;
        this.age = age;
        this.profileImage = profileImage;
        this.isFriend = isFriend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalisation() {
        return localisation;
    }

    public int getAge() {
        return age;
    }

    public String getDescription() {
        return description;
    }

    public String getinstrument() {
        return instrument;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public MusicStyle getMusicStyle() {
        return musicStyle;
    }

    public boolean getIsFriend() {
        return isFriend;
    }

}