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

public class ChatBean implements Serializable {

    private String userId;
    private String userName;
    private String userAvatar;
    private String unReadCount;
    private String Msg;
    private String userCar;
    private String time;

    public ChatBean() {
    }

    public ChatBean(String userId, String userName, String userAvatar, String unReadCount, String msg, String userCar, String time) {
        this.userId = userId;
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.unReadCount = unReadCount;
        Msg = msg;
        this.userCar = userCar;
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(String unReadCount) {
        this.unReadCount = unReadCount;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public String getUserCar() {
        return userCar;
    }

    public void setUserCar(String userCar) {
        this.userCar = userCar;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ChatBean{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", unReadCount='" + unReadCount + '\'' +
                ", Msg='" + Msg + '\'' +
                ", userCar='" + userCar + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
