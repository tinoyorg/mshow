package com.server.mshow.domain;

/*
用户报告

用户对各个对象的投诉、反馈、举报。
对象的类型区分“展馆”、“展览”和“展品”的类型，并且根据类型读取类型的编号。
报告的类型区分“投诉”、“反馈”和“举报”的类型。
 */
public class Report {

    private int rid;
    private int uid;
    private int object_id;
    private String object_type;
    private String type;
    private String content;
    private String timestamp;

    public Report() {
    }

    public Report(int rid, int uid, int object_id,
                  String object_type, String type,
                  String content, String timestamp) {
        this.rid = rid;
        this.uid = uid;
        this.object_id = object_id;
        this.object_type = object_type;
        this.type = type;
        this.content = content;
        this.timestamp = timestamp;
    }

    public int getRid() {
        return rid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getObject_id() {
        return object_id;
    }

    public void setObject_id(int object_id) {
        this.object_id = object_id;
    }

    public String getObject_type() {
        return object_type;
    }

    public void setObject_type(String object_type) {
        this.object_type = object_type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
