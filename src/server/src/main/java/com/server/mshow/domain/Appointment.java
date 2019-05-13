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
    private String status;

    public Appointment() {

    }

    public Appointment(int aid, int uid, int sid, String timestamp,
                       String lastmodify, String book_time, String status) {
        this.aid = aid;
        this.uid = uid;
        this.sid = sid;
        this.timestamp = timestamp;
        this.lastmodify = lastmodify;
        this.book_time = book_time;
        this.status = status;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
