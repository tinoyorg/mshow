package com.server.mshow.controller;


import com.server.mshow.common.JsonUtils;
import com.server.mshow.domain.Collection;
import com.server.mshow.domain.Show;
import com.server.mshow.service.CollectionService;
import com.server.mshow.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/show")
public class ShowController {

    @Autowired
    private ShowService showService;

    @Autowired
    private CollectionService collectionService;

    @GetMapping("show_list")
    public Object GetShowList(){
        JsonUtils result = new JsonUtils();

        List<Show> showList;
         try {

             showList = showService.getAllShowList();
             LinkedHashMap data = new LinkedHashMap<String,Object>();
             data.put("show_list",showList);
             result.setData(data);

         } catch (Exception e) {

             result.setStatus("500");
             result.setMsg("not found any show");
             e.printStackTrace();

             return  result.getJsonObject();
         }


        return result.getJsonObject();
    }

    @GetMapping("/{show_id}/show_content")
    public Object getShow(@PathVariable("show_id") int show_id){
        JsonUtils result = new JsonUtils();

        Show show;

        try {

            show = showService.getShow(show_id);
            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("show",show);
            result.setData(data);

        }catch (Exception e) {

            result.setStatus("500");
            result.setMsg("no this show");
            e.printStackTrace();
            return  result.getJsonObject();
        }


        return result.getJsonObject();
    }

    @GetMapping("{show_id}/collection_list")
    public Object getCollectionList(@PathVariable("show_id") int show_id){
        JsonUtils result = new JsonUtils();

        List<Collection>  collectionList;

        try {

            collectionList = collectionService.getCollectionBySid(show_id);
            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("collection_list",collectionList);
            result.setData(data);

        } catch (Exception e) {

            result.setStatus("500");
            result.setMsg("no found any collection ");
            e.printStackTrace();
            return  result.getJsonObject();
        }


        return result.getJsonObject();
    }

}
