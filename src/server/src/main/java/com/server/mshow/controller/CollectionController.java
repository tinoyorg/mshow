package com.server.mshow.controller;

import com.server.mshow.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/Collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;
}
