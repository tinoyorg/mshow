package com.server.mshow.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.server.mshow.common.TokenService;
import com.server.mshow.dao.UserMapper;
import com.server.mshow.domain.Collection;
import com.server.mshow.domain.Exhibition;
import com.server.mshow.domain.Show;
import com.server.mshow.domain.UserAuth;
import com.server.mshow.service.ToolService;
import com.server.mshow.service.UserService;
import com.server.mshow.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
public class ToolController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ToolService toolService;


    @PostMapping("/search/query")
    public Object search(@RequestBody String json,HttpServletRequest request, HttpServletResponse response){
        //body中解析code
        JSONObject jsonObject = JSON.parseObject(json);
        JsonUtils result = new JsonUtils();
        String queryStr = jsonObject.getString("queryStr");
        List<Exhibition> exhibitionList;
        List<Show> showList;
        List<Collection> collectionList;

        LinkedHashMap data = new LinkedHashMap<String,Object>();

        try {
        exhibitionList = toolService.searchExhibition(queryStr);
        showList = toolService.searchShow(queryStr);
        collectionList = toolService.searchCollection(queryStr);

        if(exhibitionList.size() != 0)
            data.put("exhibition_list",exhibitionList);
        if(showList.size() != 0)
            data.put("show_list",showList);
        if(collectionList.size() != 0)
            data.put("collection_list",collectionList);
        if(data.isEmpty())
        {
            result.setStatus("200");
            result.setMsg("Search For Nothing ");
            return  result.getJsonObject();
        }
            result.setData(data);

        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("Search Error ");
            return  result.getJsonObject();
        }

        return  result.getJsonObject();
    }
}
