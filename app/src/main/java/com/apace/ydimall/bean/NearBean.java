package com.apace.ydimall.bean;

import java.io.Serializable;

/**
 * <pre>
 *     author : Administrator
 *     e-mail : 190766172@qq.com
 *     time   : 2017-08-12
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class NearBean implements Serializable {

    private String nearId;
    private String carImg;
    private String userId;
    private String userAvatar;
    private String userName;
    private String userRole;
    private int userDistance;

    public NearBean(String nearId, String carImg, String userId, String userAvatar, String userName, String userRole, int userDistance) {
        this.nearId = nearId;
        this.carImg = carImg;
        this.userId = userId;
        this.userAvatar = userAvatar;
        this.userName = userName;
        this.userRole = userRole;
        this.userDistance = userDistance;
    }

    public NearBean() {
    }

    public String getNearId() {
        return nearId;
    }

    public void setNearId(String nearId) {
        this.nearId = nearId;
    }

    public String getCarImg() {
        return carImg;
    }

    public void setCarImg(String carImg) {
        this.carImg = carImg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public int getUserDistance() {
        return userDistance;
    }

    public void setUserDistance(int userDistance) {
        this.userDistance = userDistance;
    }

    @Override
    public String toString() {
        return "NearBean{" +
                "nearId='" + nearId + '\'' +
                ", carImg='" + carImg + '\'' +
                ", userId='" + userId + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", userName='" + userName + '\'' +
                ", userRole='" + userRole + '\'' +
                ", userDistance=" + userDistance +
                '}';
    }
}
