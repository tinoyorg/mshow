package com.server.mshow.controller;


import com.alibaba.fastjson.JSONObject;
import com.server.mshow.annotation.AdminUser;
import com.server.mshow.annotation.UserLoginToken;
import com.server.mshow.common.TokenService;
import com.server.mshow.dao.UserMapper;
import com.server.mshow.domain.Exhibition;
import com.server.mshow.domain.UserAuth;
import com.server.mshow.service.ExhibitionService;
import com.server.mshow.service.UserService;
import com.server.mshow.util.JsonUtils;
import com.server.mshow.domain.Collection;
import com.server.mshow.domain.Show;
import com.server.mshow.service.CollectionService;
import com.server.mshow.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/show")
public class ShowController {

    @Autowired
    private ShowService showService;

    @Autowired
    private CollectionService collectionService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;
    @Autowired
    private ExhibitionService exhibitionService;

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
            List<Collection> collectionList = getCollectionListBySid(show_id);
            data.put("show",show);
            data.put("collection_list",collectionList);
            result.setData(data);

        }catch (Exception e) {

            result.setStatus("500");
            result.setMsg("no this show");
            e.printStackTrace();
            return  result.getJsonObject();
        }


        return result.getJsonObject();
    }

    public List<Collection>  getCollectionListBySid(int show_id){

        List<Collection>  collectionList= collectionService.getCollectionListBySid(show_id);

        return collectionList;
    }


    //@UserLoginToken
    @AdminUser
    @PostMapping("/exhibition/{exhibition_id}/show_info")
    public Object createShow( @PathVariable("exhibition_id") int exhibition_id ,@RequestBody String json, HttpServletRequest request, HttpServletResponse response){
        JsonUtils result = new JsonUtils();
        JSONObject jsonObject = JSONObject.parseObject(json).getJSONObject("show");
        Show show = new Show();

        //从 http 请求头中取出 token,获取userAuth
        String token = request.getHeader("token");
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);
//        String open_id = map.get("open_id");
//        UserAuth userAuth = userService.getUserAuthByWX(open_id);

        try {
            //检验是否存在展览
            Exhibition exhibition = exhibitionService.getExhibition(exhibition_id);
            if(exhibition == null){
                result.setStatus("500");
                result.setMsg(" no found this exhibition");
                return  result.getJsonObject();
            }

            show.setEid(exhibition_id);
            show.setName(jsonObject.getString("show_name"));
            show.setPosition(jsonObject.getString("show_position"));
            show.setIntroduce(jsonObject.getString("show_introduce"));
            show.setAvatar(jsonObject.getString("show_avatar"));
            show.setOpen_time(jsonObject.getString("open_time"));
            show.setEnd_time(jsonObject.getString("end_time"));

            showService.createShow(show);
            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("show",show);
            result.setData(data);
            response.setHeader("token",map.get("token"));

        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("creat a show Error ");
            return  result.getJsonObject();
        }

        return  result.getJsonObject();
    }

    @UserLoginToken
    @AdminUser
    @PutMapping("/{show_id}/show_info")
    public Object updateShow(@PathVariable("show_id") int show_id,@RequestBody String json,
                                   HttpServletRequest request, HttpServletResponse response){
        JsonUtils result = new JsonUtils();

        JSONObject jsonObject = JSONObject.parseObject(json).getJSONObject("show");
        Show  show;

        String token = request.getHeader("token");// 从 http 请求头中取出 token
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);
//        String open_id = map.get("open_id");
//        UserAuth userAuth = userService.getUserAuthByWX(open_id);


        try {
            //验证展馆信息是否正确
            show = showService.getShow(show_id);
            if( show == null){

                result.setStatus("500");
                result.setMsg("NotFound ");
                result.setNewObject("NotFound" ,"该展览不存在该展品");
                return  result.getJsonObject();
            }

            //更新数据
            if(jsonObject.getString("show_name") != null)
                show.setName(jsonObject.getString("show_name"));
            if(jsonObject.getString("show_position") != null)
            show.setPosition(jsonObject.getString("show_position"));
            if(jsonObject.getString("show_introduce") != null)
            show.setIntroduce(jsonObject.getString("show_introduce"));
            if(jsonObject.getString("show_avatar") != null)
            show.setAvatar(jsonObject.getString("show_avatar"));
            if(jsonObject.getString("open_time") != null)
            show.setOpen_time(jsonObject.getString("open_time"));
            if(jsonObject.getString("end_time") != null)
            show.setEnd_time(jsonObject.getString("end_time"));

            showService.updateShow(show);
            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("show",show);
            result.setData(data);
            response.setHeader("token",map.get("token"));

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
    @DeleteMapping("/{show_id}/show_info")
    public Object cancelExhibition(@PathVariable("show_id") int show_id,HttpServletRequest request, HttpServletResponse response){
        JsonUtils result = new JsonUtils();
        Show show;

        String token = request.getHeader("token");// 从 http 请求头中取出 token
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);
//        String open_id = map.get("open_id");
//        UserAuth userAuth = userService.getUserAuthByWX(open_id);


        try {
            //验证展览信息是否正确
            show =  showService.getShow(show_id);
            if(show == null){
                result.setStatus("500");
                result.setMsg("Not found this show ");
                return  result.getJsonObject();
            }

           showService.deleteShow(show_id);
            response.setHeader("token",map.get("token"));

        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("cancelError ");
            return  result.getJsonObject();
        }


        return result.getJsonObject();
    }




}
