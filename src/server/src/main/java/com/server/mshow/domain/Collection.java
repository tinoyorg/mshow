package com.server.mshow.domain;

/*
展品
展品的相关信息
 */
public class Collection {
    private int cid;
    private int eid;
    private int sid;
    private String name;
    private String avatar;
    private String position;
    private String introduce;
    private String author;
    private String years;
    private String source;
    private String code;
    private int like;
    private int star;
    private int share;

    public Collection() {
    }

    public Collection(int cid, int eid, int sid, String name,
                      String avatar, String position, String introduce,
                      String author, String years, String source,
                      String code, int like, int star, int share) {
        this.cid = cid;
        this.eid = eid;
        this.sid = sid;
        this.name = name;
        this.avatar = avatar;
        this.position = position;
        this.introduce = introduce;
        this.author = author;
        this.years = years;
        this.source = source;
        this.code = code;
        this.like = like;
        this.star = star;
        this.share = share;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
