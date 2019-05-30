package com.server.mshow.common;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;

import java.util.Map;


@Service
public class WxService {

    @Autowired
    private WxAuth wxAuth;



    @SuppressWarnings("unchecked")
    public Map<String,Object> getWxSession(String wxCode){
        StringBuffer sb = new StringBuffer();
        sb.append("appid=").append(wxAuth.getAppId());
        sb.append("&secret=").append(wxAuth.getSecret());
        sb.append("&js_code=").append(wxCode);
        sb.append("&grant_type=").append(wxAuth.getGrantType());
        String res = HttpRequest.sendGet(wxAuth.getSessionHost(), sb.toString());

        if(res == null || "".equals(res)){
            return null;
        }
        return JSON.parseObject(res, Map.class);
    }

    public Map<String,Object> getAccessToken(){

        StringBuffer sb = new StringBuffer();
        sb.append("grant_type=").append("client_credential");
        sb.append("&appid=").append(wxAuth.getAppId());
        sb.append("&secret=").append(wxAuth.getAppSecret());
        String res = HttpRequest.sendGet(wxAuth.getTokenHost(), sb.toString());

        if(res == null || "".equals(res)){
            return null;
        }
        return JSON.parseObject(res, Map.class);
    }

    public Map<String,Object> getWxacodeunlimit(String access_token, JSONObject paramJson){

        StringBuffer sb = new StringBuffer();
        sb.append("access_token=").append(access_token);
        String res = HttpRequest.sendPost(wxAuth.getAcodeUnlimitHost(),sb.toString(),paramJson);

        if(res == null || "".equals(res)){
            return null;
        }
        return JSON.parseObject(res, Map.class);
    }
}
