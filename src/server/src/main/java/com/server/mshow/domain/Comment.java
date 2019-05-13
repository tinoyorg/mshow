package com.server.mshow.domain;

/*
用户评论

用户的评论信息。
对象的类型区分“展馆”、“展览”和“展品”的类型，并且根据类型读取类型的编号。
 */
public class Comment {
    private int cid;
    private int uid;
    private int object_id;
    private String object_type;
    private String content;
    private String timestamp;
    private int like;

    public Comment() {
    }

    public Comment(int cid, int uid, int object_id,
                   String object_type, String content,
                   String timestamp, int like) {
        this.cid = cid;
        this.uid = uid;
        this.object_id = object_id;
        this.object_type = object_type;
        this.content = content;
        this.timestamp = timestamp;
        this.like = like;
    }

    public int getCid() {
        return cid;
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

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
