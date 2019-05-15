package com.server.mshow.controller;

import com.server.mshow.common.JsonUtils;
import com.server.mshow.domain.Exhibition;
import com.server.mshow.service.ExhibitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/exhibition")
public class ExhibitionController {

    @Autowired
    private ExhibitionService exhibitionService;

    @GetMapping("/exhibition_list")
    public Object getExhibitionList(){
        JsonUtils result = new JsonUtils();

        List<Exhibition>  exhibitionList = exhibitionService.getAllExhibitionList();
//        List<Exhibition>  exhibitionList = new ArrayList<Exhibition>();
//        exhibitionList.add(new Exhibition(1,"ex1"));
//        exhibitionList.add(new Exhibition(2,"ex2"));
        if(exhibitionList == null){
            result.setStatus("500");
            result.setMsg("no any exhibition");
            return  result.getJsonObject();
        }

        LinkedHashMap data = new LinkedHashMap<String,Object>();
        data.put("exhibition_list",exhibitionList);
        result.setData(data);

        return result.getJsonObject();
    }


    @GetMapping("/{exhibition_id}/exhibition_content")
    public Object getExhibition(@PathVariable("exhibition_id") int exhibition_id){
        JsonUtils result = new JsonUtils();


        Exhibition exhibition = exhibitionService.getExhibition(exhibition_id);
        if(exhibition == null){
            result.setStatus("500");
            result.setMsg("no this exhibition");
            return  result.getJsonObject();
        }

        LinkedHashMap data = new LinkedHashMap<String,Object>();
        data.put("exhibition",exhibition);
        result.setData(data);

        return result.getJsonObject();
    }
}