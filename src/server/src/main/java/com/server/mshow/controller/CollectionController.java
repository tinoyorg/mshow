package com.server.mshow.controller;

import com.alibaba.fastjson.JSONObject;
import com.server.mshow.annotation.AdminUser;
import com.server.mshow.annotation.UserLoginToken;
import com.server.mshow.common.TokenService;
import com.server.mshow.domain.*;
import com.server.mshow.service.ExhibitionService;
import com.server.mshow.service.ShowService;
import com.server.mshow.service.UserService;
import com.server.mshow.util.JsonUtils;
import com.server.mshow.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private ShowService showService;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    @Autowired
    private ExhibitionService exhibitionService;

    @Autowired
    private ActionController actionController;


    @GetMapping("/{collection_id}/collection_content")
    public Object getCollection(@PathVariable("collection_id") int collection_id){
        JsonUtils result = new JsonUtils();

        Collection collection ;
        try {
            collection = collectionService.getCollection(collection_id);
            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("collection",collection);
            List<Comment> commentList = actionController.getPartComment_list(collection_id);
            data.put("comment_list",commentList);
            result.setData(data);

        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("no this collection");
            return  result.getJsonObject();
        }

        return result.getJsonObject();
    }

    @UserLoginToken
    @AdminUser
    @PostMapping("/show/{show_id}/collection_info")
    public Object createShow(@PathVariable("show_id") int show_id , @RequestBody String json, HttpServletRequest request, HttpServletResponse response){
        JsonUtils result = new JsonUtils();
        JSONObject jsonObject = JSONObject.parseObject(json).getJSONObject("collection");
        Collection collection = new Collection();

        //从 http 请求头中取出 token,获取userAuth
        String token = request.getHeader("X-Token");
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);
//        String open_id = map.get("open_id");
//        UserAuth userAuth = userService.getUserAuthByWX(open_id);

        try {
            //检验是否存在展览
            Show show = showService.getShow(show_id);
            if(show == null){
                result.setStatus("500");
                result.setMsg(" no found this show");
                return  result.getJsonObject();
            }
            collection.setEid(show.getEid());
            collection.setSid(show_id);
            collection.setName(jsonObject.getString("collection_name"));
            collection.setPosition(jsonObject.getString("collection_position"));
            collection.setIntroduce(jsonObject.getString("collection_introduce"));
            collection.setAvatar(jsonObject.getString("collection_avatar"));
            collection.setAuthor(jsonObject.getString("collection_author"));
            collection.setYears(jsonObject.getString("collection_years"));
            collection.setSource(jsonObject.getString("collection_source"));
            collection.setCode(jsonObject.getString("collection_code"));

            collectionService.createCollection(collection);
            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("collection",collection);
            result.setData(data);
            response.setHeader("X-Token",map.get("X-Token"));

        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("creat a collection Error ");
            return  result.getJsonObject();
        }

        return  result.getJsonObject();
    }

    @UserLoginToken
    @AdminUser
    @PutMapping("/{collection_id}/collection_info")
    public Object updateShow(@PathVariable("collection_id") int collection_id,@RequestBody String json,
                             HttpServletRequest request, HttpServletResponse response){
        JsonUtils result = new JsonUtils();

        JSONObject jsonObject = JSONObject.parseObject(json).getJSONObject("collection");
        Collection collection;

        String token = request.getHeader("X-Token");// 从 http 请求头中取出 token
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);
//        String open_id = map.get("open_id");
//        UserAuth userAuth = userService.getUserAuthByWX(open_id);


        try {
            //验证展馆信息是否正确
            collection =collectionService.getCollection(collection_id);
            if( collection == null){

                result.setStatus("500");
                result.setMsg("NotFound ");
                result.setNewObject("NotFound" ,"不存在该展品");
                return  result.getJsonObject();
            }

            //更新数据
            if(jsonObject.getString("collection_name") != null)
                collection.setName(jsonObject.getString("collection_name"));
            if(jsonObject.getString("collection_position") != null)
                collection.setPosition(jsonObject.getString("collection_position"));
            if(jsonObject.getString("collection_introduce") != null)
                collection.setIntroduce(jsonObject.getString("collection_introduce"));
            if(jsonObject.getString("collection_avatar") != null)
                collection.setAvatar(jsonObject.getString("collection_avatar"));
            if(jsonObject.getString("collection_author") != null)
                collection.setAuthor(jsonObject.getString("collection_author"));
            if(jsonObject.getString("collection_years") != null)
                collection.setYears(jsonObject.getString("collection_years"));
            if(jsonObject.getString("collection_source") != null)
                collection.setSource(jsonObject.getString("collection_source"));
            if(jsonObject.getString("collection_code") != null)
                collection.setCode(jsonObject.getString("collection_code"));

            collectionService.updateCollection(collection);
            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("collection",collection);
            result.setData(data);
            response.setHeader("X-Token",map.get("X-Token"));

        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("updateError ");
            return  result.getJsonObject();
        }
        return result.getJsonObject();
    }


    @UserLoginToken
    @AdminUser
    @DeleteMapping("/{collection_id}/collection_info")
    public Object cancelExhibition(@PathVariable("collection_id") int collection_id,HttpServletRequest request, HttpServletResponse response){
        JsonUtils result = new JsonUtils();
        Collection collection;

        String token = request.getHeader("X-Token");// 从 http 请求头中取出 token
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);
        String open_id = map.get("open_id");
        UserAuth userAuth = userService.getUserAuthByWX(open_id);


        try {
            //验证展品信息是否正确
            collection =  collectionService.getCollection(collection_id);
            if(collection == null){
                result.setStatus("500");
                result.setMsg("Not found this collection ");
                return  result.getJsonObject();
            }
            actionController.deleteAllThingByObject(userAuth.getUid(),collection_id,"collection");
            collectionService.deleteCollection(collection_id);
            response.setHeader("X-Token",map.get("X-Token"));

        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("cancelError ");
            return  result.getJsonObject();
        }


        return result.getJsonObject();
    }

}
