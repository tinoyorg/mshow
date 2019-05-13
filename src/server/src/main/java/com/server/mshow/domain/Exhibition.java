package com.server.mshow.domain;

/*
展馆
展馆的相关信息
 */
public class Exhibition {
    private int eid;
    private int uid;
    private String name;
    private String phone;
    private String avatar;
    private String position;
    private String introduce;
    private String open_time;
    private String end_time;
    private int like;
    private int star;
    private int share;

    public Exhibition() {
    }

    public Exhibition(int eid, int uid, String name,
                      String phone, String avatar,
                      String position, String introduce,
                      String open_time, String end_time,
                      int like, int star, int share) {
        this.eid = eid;
        this.uid = uid;
        this.name = name;
        this.phone = phone;
        this.avatar = avatar;
        this.position = position;
        this.introduce = introduce;
        this.open_time = open_time;
        this.end_time = end_time;
        this.like = like;
        this.star = star;
        this.share = share;
    }

    public int getEid() {
        return eid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }


}
