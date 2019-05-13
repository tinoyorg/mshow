package com.server.mshow.domain;

/*
浏览记录

用户对各个对象的浏览记录。
对象的类型区分“展馆”、“展览”和“展品”的类型，并且根据类型读取类型的编号。
 */
public class Record {
    private int rid;
    private int uid;
    private int object_id;
    private String object_type;
    private String timestamp;

    public Record() {
    }

    public Record(int rid, int uid, int object_id,
                  String object_type, String timestamp) {
        this.rid = rid;
        this.uid = uid;
        this.object_id = object_id;
        this.object_type = object_type;
        this.timestamp = timestamp;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
