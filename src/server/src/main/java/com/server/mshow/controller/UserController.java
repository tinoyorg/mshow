package com.server.mshow.controller;

import com.alibaba.fastjson.JSONObject;
import com.server.mshow.annotation.UserLoginToken;
import com.server.mshow.common.TokenService;
import com.server.mshow.domain.*;
import com.server.mshow.service.*;
import com.server.mshow.util.AesCbcUtil;
import com.server.mshow.util.JsonUtils;
import com.server.mshow.common.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
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
    private  TokenService tokenService;

    @Autowired
    private ExhibitionService exhibitionService;
    @Autowired
    private StarService starService;
    @Autowired
    private AppointmentService appointmentService;


    @PostMapping("/login")
    public Object login(@RequestBody String json,HttpServletRequest request, HttpServletResponse response){
        //body中解析code
        JSONObject jsonObject = JSON.parseObject(json);
        String wxCode = jsonObject.getString("code");
        String wxEncryptedData =jsonObject.getString("encryptedData");
        String wxIv = jsonObject.getString("iv");
        System.out.println("wxCode:" + wxCode);
        System.out.println("wxEncryptedData : " + wxEncryptedData);
        System.out.println("wxIv :"+wxIv);

        Map<String,Object> wxSessionMap = wxService.getWxSession(wxCode);
        JsonUtils result = new JsonUtils();

        if(wxSessionMap == null){
            result.setStatus("404");
            result.setMsg("Error code");
            return result.getJsonObject();
        }

        String wxOpenId = (String)wxSessionMap.get("openid");
        String wxSessionKey = (String)wxSessionMap.get("session_key");
        UserAuth userAuth = userService.getUserAuthByWX(wxOpenId);
        if(userAuth == null) {
            //创建新用户到userAuth及userInfo
            userAuth = new UserAuth();
            userAuth.setOpenid(wxOpenId);
            userAuth.setSession_key(wxSessionKey);

            userAuth.setAuth("user");

        }else {
            //旧用户重新登录，验证session_key需要更新
            if(!wxSessionKey.equals(userAuth.getSession_key())){
                userAuth.setSession_key(wxSessionKey);
            }
        }


        LinkedHashMap<Object,Object> map = initUserInfo(wxSessionKey, wxEncryptedData, wxIv);
        String wxUnionid = (String) map.get("unionId");
        UserInfo userInfo = (UserInfo) map.get("userInfo");
        System.out.println("wxUnionid :"+wxUnionid);
        userAuth.setUnionid(wxUnionid);

        //插入用户角色到数据库
        userService.insertUserAuth(userAuth);

        //插入用户信息到数据库
        userService.insertUserInfo(userInfo);
        String token = tokenService.getToken(userAuth);
        response.setHeader("token",token);

        System.out.println("openid: "+userAuth.getOpenid() );
        System.out.println("session_key: " + userAuth.getSession_key());
        System.out.println("token："+token);

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


    public LinkedHashMap<Object,Object> initUserInfo( String wxSessionKey, String encryptedData, String iv ){

        LinkedHashMap<Object,Object> map = new LinkedHashMap<>();
        UserInfo userInfo =new UserInfo();
        String unionId = "";

        try {
            //拿到用户session_key和用户敏感数据进行解密，拿到用户信息。
            String decrypts= AesCbcUtil.decrypt(encryptedData,wxSessionKey,iv,"utf-8");//解密
            JSONObject jsons  = JSON.parseObject(decrypts);
            System.out.println(jsons);
            userInfo.setNick(jsons.get("nickName").toString()); //用户昵称
            userInfo.setAvatar(jsons.get("avatarUrl").toString());//用户头像
            userInfo.setSex(jsons.get("gender").toString());//用户性别
            userInfo.setName(jsons.get("nickName").toString());//用户姓名 用昵称代替

            String position = jsons.get("province").toString()+"," //用户地址
                            + jsons.get("city").toString()+","
                            + jsons.get("country").toString();

            userInfo.setPosition(position);
            userInfo.setLocation("uninitialized");


            unionId = jsons.getString("unionId");

            System.out.println("unionId :"+unionId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        map.put("userInfo",userInfo);
        map.put("unionId",unionId);
        return map;
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
            int uid = userAuth.getUid();
            userInfo = userService.getUserInfo(uid);

            List<Star> starList  = starService.getStarByUser(uid);
            List<Appointment> appointmentList  = appointmentService.getAppointmentListByUid(uid) ;
            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("user_info",userInfo);
            data.put("auth",userAuth.getAuth());
            data.put("appointment_List" ,appointmentList);
            data.put("star_list",starList);

            if(userAuth.getAuth().equals("admin")){
            List<Exhibition>  exhibitionList = exhibitionService.getExhibitionListByUid(uid);
            data.put("exhibition_List",exhibitionList);

            }
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
    public Object updateUserInfo( @RequestBody String json, HttpServletRequest request,HttpServletResponse response){
        JsonUtils result = new JsonUtils();

        JSONObject jsonObject = JSONObject.parseObject(json).getJSONObject("user_info");
        UserInfo userInfo;

        String token = request.getHeader("token");// 从 http 请求头中取出 token
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);
        String open_id = map.get("open_id");
        UserAuth userAuth = userService.getUserAuthByWX(open_id);

        try {
            userInfo = userService.getUserInfo(userAuth.getUid());
            if(jsonObject.getString("user_name") != null)
            userInfo.setName(jsonObject.getString("user_name"));
            if(jsonObject.getString("user_location") != null)
            userInfo.setLocation(jsonObject.getString("user_location"));
            userService.updateUserInfo(userInfo);

            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("user_info",userInfo);
            result.setData(data);
            response.setHeader("token",map.get("token"));

        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("updateError ");
            return  result.getJsonObject();
        }

        return result.getJsonObject();
    }




}
