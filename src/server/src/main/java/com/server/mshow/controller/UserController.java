package com.server.mshow.controller;

import com.server.mshow.common.JsonUtils;
import com.server.mshow.common.WxService;
import com.server.mshow.domain.UserInfo;
import com.server.mshow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.PastOrPresent;
import java.util.Map;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WxService wxService;

    @PostMapping("/login")
    public Object login(@RequestParam(required = true,value = "code")String wxCode){
        Map<String,Object> wxSessionMap = wxService.getWxSession(wxCode);
        JsonUtils result = new JsonUtils();

        if(wxSessionMap == null){

        }


        UserInfo user =new UserInfo();
        result.setData(user);
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
