package com.server.mshow.controller;

import com.server.mshow.util.JsonUtils;
import com.server.mshow.domain.Exhibition;
import com.server.mshow.domain.Show;
import com.server.mshow.service.ExhibitionService;
import com.server.mshow.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/exhibition")
public class ExhibitionController {

    @Autowired
    private ExhibitionService exhibitionService;

    @Autowired
    private ShowService showService;

    @GetMapping("/exhibition_list")
    public Object getExhibitionList(){
        JsonUtils result = new JsonUtils();

        List<Exhibition>  exhibitionList;

        try {
            exhibitionList = exhibitionService.getAllExhibitionList();
            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("exhibition_list",exhibitionList);
            result.setData(data);

        } catch (Exception e) {

            result.setStatus("500");
            result.setMsg("no any exhibition");
            e.printStackTrace();
            return  result.getJsonObject();

        }


        return result.getJsonObject();
    }


    @GetMapping("/{exhibition_id}/exhibition_content")
    public Object getExhibition(@PathVariable("exhibition_id") int exhibition_id){
        JsonUtils result = new JsonUtils();

        Exhibition exhibition;

        try {
            exhibition = exhibitionService.getExhibition(exhibition_id);
            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("exhibition",exhibition);
            result.setData(data);

        } catch (Exception e) {
            result.setStatus("500");
            result.setMsg("no this exhibition");
            e.printStackTrace();
            return  result.getJsonObject();
        }


        return result.getJsonObject();
    }

    @GetMapping("{exhibition_id}/show_list")
    public Object getShowList(@PathVariable("exhibition_id") int exhibition_id){
        JsonUtils result = new JsonUtils();

        List<Show>  showList;

        try {
            showList = showService.getShowListByEid(exhibition_id);
            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("show_list",showList);
            result.setData(data);

        } catch (Exception e) {
            result.setStatus("500");
            result.setMsg("no any show");
            e.printStackTrace();
            return  result.getJsonObject();
        }


        return result.getJsonObject();
    }

}