package com.suonk.socialmusician.model;

import java.util.List;

public class User {
    private String uid;
    private String name;
    private String mail;
    private String password;
    private String localisation;
    private String description;
    private String instrument;
    private List<MusicStyle> listOfMusicStyle;
    private String profileImage;
    private String age;

    public User(String uid, String name, String mail, String password, String profileImage, String age, String localisation, String description, String instrument) {
        this.uid = uid;
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.localisation = localisation;
        this.description = description;
        this.instrument = instrument;
//        this.listOfMusicStyle = listOfMusicStyle;
        this.age = age;
        this.profileImage = profileImage;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
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

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public List<MusicStyle> getMusicStyle() {
        return listOfMusicStyle;
    }

    public void setMusicStyle(List<MusicStyle> musicStyle) {
        this.listOfMusicStyle = musicStyle;
    }
}
