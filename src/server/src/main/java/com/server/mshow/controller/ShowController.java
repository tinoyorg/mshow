package com.server.mshow.controller;


import com.server.mshow.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/show")
public class ShowController {

    @Autowired
    private ShowService showService;

}
