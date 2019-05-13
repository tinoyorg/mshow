package com.server.mshow.domain;


/*
用户权限
用户鉴权所需信息
 */
public class UserAuth {
    private int uid;
    private String openid;
    private String session_key;
    private String unionid;
    private String auth;

    public UserAuth() {

    }
    public UserAuth(int uid, String openid, String session_key,
                    String unionid, String auth) {
        this.uid = uid;
        this.openid = openid;
        this.session_key = session_key;
        this.unionid = unionid;
        this.auth = auth;
    }

    public int getUid() {
        return uid;
    }


    public String getOpenid() {
        return openid;
    }

    public String getSession_key() {
        return session_key;
    }

    public String getUnionid() {
        return unionid;
    }

    public String getAuth() {
        return auth;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }


}
