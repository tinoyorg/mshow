package com.server.mshow.controller;

import com.alibaba.fastjson.JSONObject;
import com.server.mshow.annotation.UserLoginToken;
import com.server.mshow.common.TokenService;
import com.server.mshow.dao.UserMapper;
import com.server.mshow.domain.*;
import com.server.mshow.service.*;
import com.server.mshow.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
public class ActionController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;
    @Autowired
    private RecordService recordService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private CollectionService collectionService;
    @Autowired
    private ShowService showService;
    @Autowired
    private ExhibitionService exhibitionService;
    @Autowired
    private  StarService starService;

    @UserLoginToken
    @GetMapping("/user/appointment_list")
    public Object getAppointmentList(HttpServletRequest request,HttpServletResponse response){
        JsonUtils result = new JsonUtils();
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);
        String open_id = map.get("open_id");
        UserAuth userAuth = userService.getUserAuthByWX(open_id);

        try {
            List<Appointment> appointment_list= appointmentService.getAppointmentListByUid(userAuth.getUid());
            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("appointment_list",appointment_list);
            result.setData(data);
            response.setHeader("token",map.get("token"));

        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("An Error In Getting A Appointment_list");
            return  result.getJsonObject();
        }

        return result.getJsonObject();
    }

    @UserLoginToken
    @GetMapping("/user/record_list")
    public Object getRecordList(HttpServletRequest request,HttpServletResponse response){
        JsonUtils result = new JsonUtils();
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);
        String open_id = map.get("open_id");
        UserAuth userAuth = userService.getUserAuthByWX(open_id);

        try {
            List<Record> record_list = recordService.getRecordByUser(userAuth.getUid());
            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("record_list",record_list);
            result.setData(data);
            response.setHeader("token",map.get("token"));

        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("An Error In Getting A Record_list");
            return  result.getJsonObject();
        }

        return result.getJsonObject();
    }


    @GetMapping("/comment_list/object_type/{object_type}/object_id/{object_id}")
    public Object getMoreComment_list(@PathVariable("object_type") String object_type, @PathVariable("object_id") int object_id){
        JsonUtils result = new JsonUtils();

        try {

            List<Comment> comment_list = commentService.getCommentListByObject(object_id);
            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("comment_list",comment_list);
            result.setData(data);


        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("An Error In Getting A Comment_list");
            return  result.getJsonObject();
        }

        return result.getJsonObject();
    }

    public List<Comment> getPartComment_list(int object_id){

        List<Comment> comment_list = commentService.getCommentListByObject(object_id).subList(0,2);

        return comment_list;
    }

    @UserLoginToken
    @PostMapping("/user/object_type/{object_type}/object_id/{object_id}/comment")
    public Object createComment(@RequestBody String json, @PathVariable("object_type") String object_type, @PathVariable("object_id") int object_id,
                                HttpServletRequest request, HttpServletResponse response){
        JsonUtils result = new JsonUtils();
        JSONObject jsonObject = JSONObject.parseObject(json);
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);
        String open_id = map.get("open_id");
        UserAuth userAuth = userService.getUserAuthByWX(open_id);
        try {
            Comment comment = new Comment();

            comment.setUid(userAuth.getUid());
            comment.setObject_id(object_id);
            comment.setObject_type(object_type);
            Date date = new Date();
            //设置要获取到什么样的时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //获取String类型的时间
            String  timestamp= sdf.format(date);
            comment.setTimestamp(timestamp);

            if(jsonObject.getString("content") != null)
                comment.setContent(jsonObject.getString("content"));
            commentService.createComment(comment);


            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("comment",comment);
            result.setData(data);
            response.setHeader("token",map.get("token"));


        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("An Error In Creating A Comment");
            return  result.getJsonObject();
        }

        return result.getJsonObject();
    }

    @UserLoginToken
    @DeleteMapping("/user/object_type/{object_type}/object_id/{object_id}/comment/{comment_id}")
    public Object deleteComment(@PathVariable("object_type") String object_type, @PathVariable("object_id") int object_id,
                                @PathVariable("comment_id") int comment_id, HttpServletRequest request, HttpServletResponse response){
        JsonUtils result = new JsonUtils();

        String token = request.getHeader("token");// 从 http 请求头中取出 token
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);


        try {
            commentService.cancelComment(comment_id);

            response.setHeader("token",map.get("token"));


        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("An Error In Deleting A Record_list");
            return  result.getJsonObject();
        }

        return result.getJsonObject();
    }

    @UserLoginToken
    @PostMapping("/user/object_type/{object_type}/object_id/{object_id}/like")
    public Object createLike(@PathVariable("object_type") String object_type, @PathVariable("object_id") int object_id,
                             HttpServletRequest request, HttpServletResponse response){
        JsonUtils result = new JsonUtils();
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);

        try {
            int like = 0;

           if(object_type.equals("collection")) {
               Collection collection;
               collection = collectionService.getCollection(object_id);

               like = collection.getLike();
               like++;
               collection.setLike(like);
               collectionService.updateCollection(collection);
           } else if(object_type.equals("show")){
               Show show;
               show = showService.getShow(object_id);

               like = show.getLike();
               like++;
               show.setLike(like);
               showService.updateShow(show);
           }else if(object_type.equals("exhibition")){
               Exhibition exhibition;
               exhibition = exhibitionService.getExhibition(object_id);

               like = exhibition.getLike();
               like++;
               exhibition.setLike(like);
               exhibitionService.updateExhibition(exhibition);
           }else{
               result.setStatus("500");
               result.setMsg("An Error In Object_type");
               return  result.getJsonObject();
           }

            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("like_count",like);
            result.setData(data);

            response.setHeader("token",map.get("token"));


        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("An Error In Liking a " + object_type);
            return  result.getJsonObject();
        }


        return result.getJsonObject();
    }

    @UserLoginToken
    @DeleteMapping("/user/object_type/{object_type}/object_id/{object_id}/like")
    public Object cancelLike(@PathVariable("object_type") String object_type, @PathVariable("object_id") int object_id,
                             HttpServletRequest request, HttpServletResponse response){
        JsonUtils result = new JsonUtils();
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);

        try {
            int like = 0;
            if(object_type.equals("collection")) {
                Collection collection;
                collection = collectionService.getCollection(object_id);

                like = collection.getLike();
                like--;
                collection.setLike(like);
                collectionService.updateCollection(collection);
            } else if(object_type.equals("show")){
                Show show;
                show = showService.getShow(object_id);

                like = show.getLike();
                like--;
                show.setLike(like);
                showService.updateShow(show);
            }else if(object_type.equals("exhibition")){
                Exhibition exhibition;
                exhibition = exhibitionService.getExhibition(object_id);

                like = exhibition.getLike();
                like--;
                exhibition.setLike(like);
                exhibitionService.updateExhibition(exhibition);
            }else{
                result.setStatus("500");
                result.setMsg("An Error In Object_type");
                return  result.getJsonObject();
            }

            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("like_count",like);
            result.setData(data);

            response.setHeader("token",map.get("token"));


        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("An Error In Liking  a " + object_type);
            return  result.getJsonObject();
        }


        return result.getJsonObject();
    }

    @UserLoginToken
    @PostMapping("/user/object_type/{object_type}/object_id/{object_id}/star")
    public Object createStar(@PathVariable("object_type") String object_type, @PathVariable("object_id") int object_id,
                             HttpServletRequest request, HttpServletResponse response){
        JsonUtils result = new JsonUtils();
        Star star = new Star();
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);
        String open_id = map.get("open_id");
        UserAuth userAuth = userService.getUserAuthByWX(open_id);

        try {

            Date date = new Date();
            //设置要获取到什么样的时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //获取String类型的时间
            String  timestamp= sdf.format(date);
            star.setTimestamp(timestamp);

            star.setUid(userAuth.getUid());
            star.setObject_id(object_id);
            star.setObject_type(object_type);

            starService.createStar(star);

            //增加对象的收藏数
            int star_count = 0;
            if(object_type.equals("collection")) {
                Collection collection;
                collection = collectionService.getCollection(object_id);

                star_count = collection.getStar();
                star_count++;
                collection.setStar(star_count);
                collectionService.updateCollection(collection);

            } else if(object_type.equals("show")){
                Show show;
                show = showService.getShow(object_id);

                star_count = show.getStar();
                star_count++;
                show.setStar(star_count);
                showService.updateShow(show);
            }else if(object_type.equals("exhibition")){
                Exhibition exhibition;
                exhibition = exhibitionService.getExhibition(object_id);

                star_count = exhibition.getStar();
                star_count++;
                exhibition.setStar(star_count);
                exhibitionService.updateExhibition(exhibition);
            }else{
                result.setStatus("500");
                result.setMsg("An Error In Object_type");
                return  result.getJsonObject();
            }

            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("star_count",star_count);
            result.setData(data);

            response.setHeader("token",map.get("token"));


        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("An Error In Staring a " + object_type);
            return  result.getJsonObject();
        }


        return result.getJsonObject();
    }

    @UserLoginToken
    @PostMapping("/user/object_type/{object_type}/object_id/{object_id}/star/{star_id}")
    public Object cancelStar(@PathVariable("object_type") String object_type, @PathVariable("object_id") int object_id,
                             @PathVariable("star_id") int star_id,HttpServletRequest request, HttpServletResponse response){
        JsonUtils result = new JsonUtils();

        String token = request.getHeader("token");// 从 http 请求头中取出 token
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);


        try {

            starService.cancelStar(star_id);

            //增加对象的收藏数
            int star_count = 0;
            if(object_type.equals("collection")) {
                Collection collection;
                collection = collectionService.getCollection(object_id);

                star_count = collection.getStar();
                star_count--;
                collection.setStar(star_count);
                collectionService.updateCollection(collection);

            } else if(object_type.equals("show")){
                Show show;
                show = showService.getShow(object_id);

                star_count = show.getStar();
                star_count--;
                show.setStar(star_count);
                showService.updateShow(show);
            }else if(object_type.equals("exhibition")){
                Exhibition exhibition;
                exhibition = exhibitionService.getExhibition(object_id);

                star_count = exhibition.getStar();
                star_count--;
                exhibition.setStar(star_count);
                exhibitionService.updateExhibition(exhibition);
            }else{
                result.setStatus("500");
                result.setMsg("An Error In Object_type");
                return  result.getJsonObject();
            }

            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("star_count",star_count);
            result.setData(data);

            response.setHeader("token",map.get("token"));


        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("An Error In Staring a " + object_type);
            return  result.getJsonObject();
        }


        return result.getJsonObject();
    }

}