package com.server.mshow.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToolController {

    @GetMapping("/search/{query}")
    public Object search(@PathVariable("query") String query){

        return new Object();
    }
}
