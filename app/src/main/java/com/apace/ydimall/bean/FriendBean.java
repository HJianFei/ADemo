package com.apace.ydimall.bean;


import java.io.Serializable;

public class FriendBean implements Serializable {
    public String id;
    public String username;
    public String avatar_img;
    public String letters;

    public FriendBean(String id, String username, String avatar_img, String letters) {
        this.id = id;
        this.username = username;
        this.avatar_img = avatar_img;
        this.letters = letters;
    }

    public FriendBean() {
    }

    public FriendBean(String id, String username, String avatar_img) {
        this.id = id;
        this.username = username;
        this.avatar_img = avatar_img;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", letters='" + letters + '\'' +
                '}';
    }
}
