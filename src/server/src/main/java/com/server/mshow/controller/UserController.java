package com.server.mshow.controller;

import com.server.mshow.service.UserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.PastOrPresent;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/order_list/{user_id}")
    public Object getOrder(@PathVariable("user_id") String user_id){
        JSONObject result = new JSONObject();
        result.put("status","success");
        return result ;
    }

    @PostMapping("/login")
    public Object login(){
        JSONObject result = new JSONObject();
        result.put("status","success");
        return result;
    }

    @GetMapping("/user_info/{user_id}")
    public Object getUserInfo(@PathVariable("user_id") String user_id){
        JSONObject result = new JSONObject();
        result.put("status","success");
        return result;
    }

    @PutMapping("/user_info/{user_id}")
    public Object updateUserInfo(@PathVariable("user_id") String user_id){
        JSONObject result = new JSONObject();
        result.put("status","success");
        return result;
    }

    @PostMapping("/like")
    public Object like(){
        JSONObject result = new JSONObject();
        result.put("status","success");
        return result;
    }
}
