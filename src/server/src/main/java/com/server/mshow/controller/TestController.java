package com.server.mshow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController

public class TestController {
    @GetMapping("/test")
    public Map<String, String> test(){
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("status", "success");
        hashMap.put("info", "Hello, world!");
        return hashMap;
    }
}
