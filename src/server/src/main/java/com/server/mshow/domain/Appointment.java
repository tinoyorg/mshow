package com.server.mshow.domain;

/*
预约记录

用户对展览的预约的信息记录。
状态的类型区分“预约成功”、“完成预约”和“取消预约”的类型，并且根据类型读取类型的编号
 */
public class Appointment {
    private int aid;
    private int uid;
    private int sid;
    private String timestamp;
    private String lastmodify;
    private String book_time;
    private String arrival_time;
    private String staus;
    private String name;
    private String avatar;
    private int like;
    private int star;
    private int share;

    public Appointment() {

    }

    public Appointment(int aid, int uid, int sid, String timestamp,
                       String lastmodify, String book_time, String arrival_time,String staus) {
        this.aid = aid;
        this.uid = uid;
        this.sid = sid;
        this.timestamp = timestamp;
        this.lastmodify = lastmodify;
        this.book_time = book_time;
        this.arrival_time =arrival_time;
        this.staus = staus;
    }

    public int getAid() {
        return aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getLastmodify() {
        return lastmodify;
    }

    public void setLastmodify(String lastmodify) {
        this.lastmodify = lastmodify;
    }

    public String getBook_time() {
        return book_time;
    }

    public void setBook_time(String book_time) {
        this.book_time = book_time;
    }

    public String getStaus() {
        return staus;
    }

    public void setStaus(String staus) {
        this.staus = staus;
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
