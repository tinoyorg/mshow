package com.server.mshow.controller;

import com.alibaba.fastjson.JSONObject;
import com.server.mshow.common.AesCbcUtil;
import com.server.mshow.common.JsonUtils;
import com.server.mshow.common.WxService;
import com.server.mshow.domain.UserAuth;
import com.server.mshow.domain.UserInfo;
import com.server.mshow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;
import com.alibaba.fastjson.JSON;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WxService wxService;

    @PostMapping("/login")

    public Object login(@RequestBody String code,HttpServletRequest request){

        JSONObject jsonObject = JSON.parseObject(code);
        String wxCode = (String) jsonObject.get("code");
        System.out.println("wxCode:" +wxCode);
        Map<String,Object> wxSessionMap = wxService.getWxSession(wxCode);
        JsonUtils result = new JsonUtils();

        if(wxSessionMap == null){
            result.setStatus("404");
            result.setMsg("Error code");
            return result.getJsonObject();
        }

        String wxOpenId = (String)wxSessionMap.get("openid");
        String wxSessionKey = (String)wxSessionMap.get("session_key");

        UserAuth userAuth =new UserAuth();
        userAuth.setOpenid(wxOpenId);
        userAuth.setSession_key(wxSessionKey);
        userAuth.setAuth("user");

        LinkedHashMap data = new LinkedHashMap<String,Object>();
        data.put("user_Auth",userAuth);
        result.setData(data);
        return result.getJsonObject();
    }

    @PostMapping("/user_info")
    public Object initUserInfo(@RequestBody String encryptedData, @RequestBody String iv,HttpServletRequest request){
        JsonUtils result = new JsonUtils();
        //JSONObject jsonObject = JSON.parseObject(code);
        UserInfo userInfo =new UserInfo();
        String wxSessionKey="";
        try {
            //拿到用户session_key和用户敏感数据进行解密，拿到用户信息。
            String decrypts= AesCbcUtil.decrypt(encryptedData,wxSessionKey,iv,"utf-8");//解密
            JSONObject jsons  = JSON.parseObject(decrypts);
            userInfo.setNick(jsons.get("nickName").toString()); //用户昵称
            userInfo.setAvatar(jsons.get("avatarUrl").toString());//用户头像
            userInfo.setSex(jsons.get("gender").toString());
            userInfo.setName(jsons.get("nickName").toString());
            String position =jsons.get("province").toString()+jsons.get("city").toString();
            userInfo.setPosition(position);
            userInfo.setLocation(jsons.get("country").toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        LinkedHashMap data = new LinkedHashMap<String,Object>();
        data.put("user_info",userInfo);
        result.setData(data);
        return result.getJsonObject();
    }
    @GetMapping("/user_info/{user_id}")
    public Object getUserInfo(@PathVariable("user_id") String user_id){
        UserInfo user =new UserInfo();

        return user;
    }

    @PutMapping("/user_info/{user_id}")
    public Object updateUserInfo(@PathVariable("user_id") String user_id){
        JsonUtils result = new JsonUtils();

        return result;
    }

    @PostMapping("/like")
    public Object like(){
        JsonUtils result = new JsonUtils();
        return result;
    }
}
