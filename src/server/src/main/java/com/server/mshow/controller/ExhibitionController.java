package com.server.mshow.controller;

import com.server.mshow.service.ExhibitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/exhibition")
public class ExhibitionController {

    @Autowired
    private ExhibitionService exhibitionService;
}
