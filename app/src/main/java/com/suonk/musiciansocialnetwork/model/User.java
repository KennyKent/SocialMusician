package com.suonk.musiciansocialnetwork.model;

import java.util.List;

public class User {
    private String name;
    private String localisation;
    private String description;
    private String instrument;
    private List<MusicStyle> listOfMusicStyle;
    private int profileImage;
    private int age;

    public User(String name, String localisation, String description, String instrument, List<MusicStyle> listOfMusicStyle, int profileImage, int age) {
        this.name = name;
        this.localisation = localisation;
        this.description = description;
        this.instrument = instrument;
        this.listOfMusicStyle = listOfMusicStyle;
        this.age = age;
        this.profileImage = profileImage;
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

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public List<MusicStyle> getMusicStyle() {
        return listOfMusicStyle;
    }

    public void setMusicStyle(List<MusicStyle> musicStyle) {
        this.listOfMusicStyle = musicStyle;
    }
}
