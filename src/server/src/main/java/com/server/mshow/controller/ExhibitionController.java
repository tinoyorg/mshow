package com.server.mshow.controller;

import com.alibaba.fastjson.JSONObject;
import com.server.mshow.annotation.AdminUser;
import com.server.mshow.annotation.PassToken;
import com.server.mshow.annotation.UserLoginToken;
import com.server.mshow.common.TokenService;
import com.server.mshow.common.WxService;
import com.server.mshow.dao.UserMapper;
import com.server.mshow.domain.Comment;
import com.server.mshow.domain.UserAuth;
import com.server.mshow.service.UserService;
import com.server.mshow.util.JsonUtils;
import com.server.mshow.domain.Exhibition;
import com.server.mshow.domain.Show;
import com.server.mshow.service.ExhibitionService;
import com.server.mshow.service.ShowService;
import javafx.beans.binding.ObjectExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/exhibition")
public class ExhibitionController {

    @Autowired
    private ExhibitionService exhibitionService;

    @Autowired
    private ShowService showService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;
    @Autowired
    private ActionController actionController;

    @PassToken
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

    @PassToken
    @GetMapping("/{exhibition_id}/exhibition_content")
    public Object getExhibition(@PathVariable("exhibition_id") int exhibition_id){
        JsonUtils result = new JsonUtils();

        Exhibition exhibition;

        try {
            exhibition = exhibitionService.getExhibition(exhibition_id);
            List<Show>  show_List= getShowListByEid(exhibition_id);
            LinkedHashMap data = new LinkedHashMap<String,Object>();

            data.put("exhibition",exhibition);
            data.put("show_List",show_List);
            List<Comment> commentList = actionController.getPartComment_list(exhibition_id);
            data.put("comment_list",commentList);
            result.setData(data);

        } catch (Exception e) {
            result.setStatus("500");
            result.setMsg("no this exhibition");
            e.printStackTrace();
            return  result.getJsonObject();
        }


        return result.getJsonObject();
    }


    public List<Show> getShowListByEid( int exhibition_id){


        List<Show>  showList = showService.getShowListByEid(exhibition_id);

        return showList;
    }

    @UserLoginToken
    @PostMapping("/exhibition_info")
    public Object createExhibition(@RequestBody String json,HttpServletRequest request, HttpServletResponse response){
        JsonUtils result = new JsonUtils();
        JSONObject jsonObject = JSONObject.parseObject(json).getJSONObject("exhibition");
        Exhibition exhibition = new Exhibition();
        LinkedHashMap data = new LinkedHashMap<String,Object>();


        //从 http 请求头中取出 token,获取userAuth
        String token = request.getHeader("X-Token");
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);
        String open_id = map.get("open_id");
        UserAuth userAuth = userService.getUserAuthByWX(open_id);

        try {

            exhibition.setUid(userAuth.getUid());
//            exhibition.setUid(1);
            exhibition.setName(jsonObject.getString("exhibition_name"));
            exhibition.setPhone(jsonObject.getString("exhibition_phone"));
            exhibition.setAvatar(jsonObject.getString("exhibition_avatar"));
            exhibition.setPosition(jsonObject.getString("exhibition_position"));
            exhibition.setIntroduce(jsonObject.getString("exhibition_introduce"));
            exhibition.setOpen_time(jsonObject.getString("open_time"));
            exhibition.setEnd_time(jsonObject.getString("end_time"));
            exhibition.setLike(0);
            exhibition.setStar(0);
            exhibition.setShare(0);

            exhibitionService.createExhibition(exhibition);
            userAuth.setAuth("admin");
            userService.updateUserAuth(userAuth);

            data.put("exhibition",exhibition);
            data.put("auth",userAuth.getAuth());
            result.setData(data);
            response.setHeader("X-Token",map.get("X-Token"));

        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("creat a exhibition Error ");
            return  result.getJsonObject();
        }

        return  result.getJsonObject();
    }



    @UserLoginToken
    @AdminUser
    @PutMapping("/{exhibition_id}/exhibition_info")
    public Object updateExhibition(@PathVariable("exhibition_id") int exhibition_id,@RequestBody String json,
                                   HttpServletRequest request, HttpServletResponse response){
        JsonUtils result = new JsonUtils();

        JSONObject jsonObject = JSONObject.parseObject(json).getJSONObject("exhibition");
        Exhibition exhibition;

        String token = request.getHeader("X-Token");// 从 http 请求头中取出 token
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);
        String open_id = map.get("open_id");
        UserAuth userAuth = userService.getUserAuthByWX(open_id);


        try {
            //验证展馆信息是否正确
            exhibition = exhibitionService.getExhibition(exhibition_id);
            if(exhibition.getUid() != userAuth.getUid()){
                result.setStatus("500");
                result.setMsg("用户不匹配 ");
                return  result.getJsonObject();
            }

            //更新数据
            if(jsonObject.getString("exhibition_name") != null)
                exhibition.setName(jsonObject.getString("exhibition_name"));
            if(jsonObject.getString("exhibition_phone") != null)
                exhibition.setPhone(jsonObject.getString("exhibition_phone"));
            if(jsonObject.getString("exhibition_avatar") != null)
                exhibition.setAvatar(jsonObject.getString("exhibition_avatar"));
            if(jsonObject.getString("exhibition_position") != null)
                exhibition.setAvatar(jsonObject.getString("exhibition_position"));
            if(jsonObject.getString("exhibition_introduce") != null)
                exhibition.setIntroduce(jsonObject.getString("exhibition_introduce"));
            if(jsonObject.getString("open_time") != null)
                exhibition.setOpen_time(jsonObject.getString("open_time"));
            if(jsonObject.getString("end_time") != null)
                exhibition.setEnd_time(jsonObject.getString("end_time"));

            exhibitionService.updateExhibition(exhibition);
            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("exhibition",exhibition);
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


    @AdminUser
    @DeleteMapping("/{exhibition_id}/exhibition_info")
    public Object cancelExhibition(@PathVariable("exhibition_id") int exhibition_id,HttpServletRequest request, HttpServletResponse response){
        JsonUtils result = new JsonUtils();
        Exhibition exhibition;

        String token = request.getHeader("X-Token");// 从 http 请求头中取出 token
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);
        String open_id = map.get("open_id");
        UserAuth userAuth = userService.getUserAuthByWX(open_id);


        try {
            //验证展馆信息是否正确
            exhibition = exhibitionService.getExhibition(exhibition_id);
            if(exhibition.getUid() != userAuth.getUid()){
                result.setStatus("500");
                result.setMsg("用户不匹配 ");
                return  result.getJsonObject();
            }

            exhibitionService.deleteExhibition(exhibition_id);
            userAuth.setAuth("user");
            userService.updateUserAuth(userAuth);

            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("auth",userAuth.getAuth());
            result.setData(data);
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