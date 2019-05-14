package com.server.mshow.common;


import java.util.LinkedHashMap;

/*
@Description: 自定义响应结构, json转换类
 */
public class JsonUtils  {


    //定义jsonObject类
    private LinkedHashMap jsonObject;
    private String status;
    private String msg;
    private String token;
    private LinkedHashMap  data;


    public JsonUtils(){
        jsonObject = new LinkedHashMap();
        this.setStatus("200");
        this.setMsg("success");
    }

    public LinkedHashMap getJsonObject() {
        return jsonObject;
    }

    public void setNewObject(String key, Object o) {
        this.jsonObject.put(key,o);
    }

    public void setStatus(String status) {
        this.status = status;
        this.jsonObject.put("status",status);
    }

    public void setMsg(String msg) {
        this.msg = msg;
        this.jsonObject.put("msg",msg);
    }

    public void setToken(String token) {
        this.token = token;
        this.jsonObject.put("token",token);
    }

    public void setData(LinkedHashMap data) {
        this.data = data;
        this.jsonObject.put("data",data);
    }
}
