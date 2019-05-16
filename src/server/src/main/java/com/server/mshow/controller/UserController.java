package com.server.mshow.controller;

import com.alibaba.fastjson.JSONObject;
import com.server.mshow.annotation.UserLoginToken;
import com.server.mshow.common.TokenService;
import com.server.mshow.util.AesCbcUtil;
import com.server.mshow.util.JsonUtils;
import com.server.mshow.common.WxService;
import com.server.mshow.domain.UserAuth;
import com.server.mshow.domain.UserInfo;
import com.server.mshow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public Object login(@RequestBody String code, HttpServletRequest request, HttpServletResponse response){
        //body中解析code
        JSONObject jsonObject = JSON.parseObject(code);
        String wxCode = (String) jsonObject.get("code");
        System.out.println("wxCode:" + wxCode);

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
        userService.insertUserAuth(userAuth);

        String token = tokenService.getToken(userAuth);
        response.setHeader("token",token);

        LinkedHashMap data = new LinkedHashMap<String,Object>();

        data.put("user_Auth",userAuth);
        System.out.println("openid: "+userAuth.getOpenid() );
        System.out.println("session_key: " + userAuth.getSession_key());
        System.out.println("token："+token);

        result.setData(data);
        return result.getJsonObject();
    }

    @PostMapping("/token")
    public Object getToken(HttpServletRequest request,HttpServletResponse response){

        JsonUtils result = new JsonUtils();
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        System.out.println(token);
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);
        String wxSessionKey = map.get("session_key");
        response.setHeader("session_key",wxSessionKey);
        response.setHeader("old_token",token);
        return result.getJsonObject();
    }

    @UserLoginToken
    @PostMapping("/user_info")
    public Object initUserInfo(@RequestBody String encryptedData, @RequestBody String iv,
                               HttpServletRequest request,HttpServletResponse response){
        JsonUtils result = new JsonUtils();

        UserInfo userInfo =new UserInfo();

        //从token获取用户的openid和session_key
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);
        String wxSessionKey = map.get("session_key");
        System.out.println(wxSessionKey);

        //body中解析数据
        JSONObject jsonObject = JSON.parseObject(encryptedData);
        encryptedData = (String) jsonObject.get("encryptedData");
        iv =  (String) jsonObject.get("iv");

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
        userService.insertUserInfo(userInfo);
        LinkedHashMap data = new LinkedHashMap<String,Object>();
        data.put("user_info",userInfo);
        result.setData(data);

        response.setHeader("token",map.get("token"));

        return result.getJsonObject();
    }

    @UserLoginToken
    @GetMapping("/user_info")
    public Object getUserInfo(HttpServletRequest request,HttpServletResponse response){
        JsonUtils result = new JsonUtils();
        UserInfo userInfo;
        // 从 http 请求头中取出 token,并获得openid等
        String token = request.getHeader("token");
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);
        String open_id = map.get("open_id");
        UserAuth userAuth = userService.getUserAuthByWX(open_id);

        try {
            userInfo = userService.getUserInfo(userAuth.getUid());
            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("user_info",userInfo);
            result.setData(data);
            response.setHeader("token",map.get("token"));

        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("no this user");
            return  result.getJsonObject();
        }

        return result.getJsonObject();
    }

    @PutMapping("/user_info")
    public Object updateUserInfo( @RequestBody String user_info, HttpServletRequest request,HttpServletResponse response){
        JsonUtils result = new JsonUtils();

        JSONObject jsonObject = JSON.parseObject(user_info);
        JSONObject user_json = jsonObject.getJSONObject("user_info");

        String name = user_json.getString("user_name");
        System.out.println("new "+name);

        //String token = request.getHeader("token");// 从 http 请求头中取出 token
        //LinkedHashMap<String,String> map = tokenService.verifyToken(token);
        //tring open_id = map.get("open_id");
       // UserAuth userAuth = userService.getUserAuthByWX(open_id);

        try {
            //userInfo = userService.getUserInfo(userAuth.getUid());
            LinkedHashMap data = new LinkedHashMap<String,Object>();
           // data.put("user_info",userInfo);
            result.setData(data);
            //response.setHeader("token",map.get("token"));

        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("updateError ");
            return  result.getJsonObject();
        }

        return result.getJsonObject();
    }

    @PostMapping("/like")
    public Object like(){
        JsonUtils result = new JsonUtils();
        return result;
    }
}
