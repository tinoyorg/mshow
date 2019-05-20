package com.server.mshow.domain;


import com.alibaba.fastjson.annotation.JSONField;

/*
用户信息
用户更多更全面的个人基本信息
 */
public class UserInfo {




    private int uid;
    private String sex;
    private String nick;
    private String name;
    private String location;
    private String position;
    private String avatar;

    public UserInfo() {

    }

    public UserInfo(int uid, String sex, String nick,
                    String name, String location,
                    String position, String avatar) {
        this.uid = uid;
        this.sex = sex;
        this.nick = nick;
        this.name = name;
        this.location = location;
        this.position = position;
        this.avatar = avatar;
    }

    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
