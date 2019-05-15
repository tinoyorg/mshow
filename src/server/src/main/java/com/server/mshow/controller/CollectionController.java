package com.server.mshow.controller;

import com.server.mshow.common.JsonUtils;
import com.server.mshow.domain.Collection;
import com.server.mshow.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
@RequestMapping(value = "/collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @GetMapping("/{collection_id}/collection_content")
    public Object getCollection(@PathVariable("collection_id") int collection_id){
        JsonUtils result = new JsonUtils();

        Collection collection ;
        try {
            collection = collectionService.getCollection(collection_id);
            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("collection",collection);
            result.setData(data);

        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("no this collection");
            return  result.getJsonObject();
        }

        return result.getJsonObject();
    }
}
