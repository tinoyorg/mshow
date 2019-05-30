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

    @Autowired
    private ReportService reportService;


    @UserLoginToken
    @GetMapping("/appointment_list")
    public Object getAppointmentList(HttpServletRequest request,HttpServletResponse response){
        JsonUtils result = new JsonUtils();
        String token = request.getHeader("X-Token");// 从 http 请求头中取出 token
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);
        String open_id = map.get("open_id");
        UserAuth userAuth = userService.getUserAuthByWX(open_id);

        try {
            int uid = userAuth.getUid();
            System.out.println(uid);
            List<Appointment> appointment_list= appointmentService.getAppointmentListByUid(uid);
            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("appointment_list",appointment_list);
            result.setData(data);
            response.setHeader("X-Token",map.get("X-Token"));

        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("An Error In Getting A Appointment_list");
            return  result.getJsonObject();
        }

        return result.getJsonObject();
    }

    @UserLoginToken
    @GetMapping("/record_list")
    public Object getRecordList(HttpServletRequest request,HttpServletResponse response){
        JsonUtils result = new JsonUtils();
        String token = request.getHeader("X-Token");// 从 http 请求头中取出 token
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);
        String open_id = map.get("open_id");
        UserAuth userAuth = userService.getUserAuthByWX(open_id);

        try {
            List<Record> record_list = recordService.getRecordByUser(userAuth.getUid());
            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("record_list",record_list);
            result.setData(data);
            response.setHeader("X-Token",map.get("X-Token"));

        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("An Error In Getting A Record_list");
            return  result.getJsonObject();
        }

        return result.getJsonObject();
    }

    @UserLoginToken
    @PostMapping("/record/object_type/{object_type}/object_id/{object_id}")
    public Object createRecord(@PathVariable("object_type") String object_type, @PathVariable("object_id") int object_id,
                                HttpServletRequest request,HttpServletResponse response){
        JsonUtils result = new JsonUtils();
        String token = request.getHeader("X-Token");// 从 http 请求头中取出 token
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);
        String open_id = map.get("open_id");
        UserAuth userAuth = userService.getUserAuthByWX(open_id);

        Record record = new Record();
        try {
            record.setUid(userAuth.getUid());
            record.setObject_id(object_id);
            record.setObject_type(object_type);
            Date date = new Date();
            //设置要获取到什么样的时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //获取String类型的时间
            String timestamp= sdf.format(date);
            record.setTimestamp(timestamp);
            recordService.createRecord(record);
            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("record",record);
            result.setData(data);
            response.setHeader("X-Token",map.get("X-Token"));

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

        List<Comment> comment_list = commentService.getCommentListByObject(object_id);
        List<Comment> newList = null;

        if(comment_list.size()>0){
            //newList = comment_list.subList(0,1);
            newList = comment_list;
        }

        return newList;
    }

    @UserLoginToken
    @PostMapping("/object_type/{object_type}/object_id/{object_id}/comment")
    public Object createComment(@RequestBody String json, @PathVariable("object_type") String object_type, @PathVariable("object_id") int object_id,
                                HttpServletRequest request, HttpServletResponse response){
        JsonUtils result = new JsonUtils();
        JSONObject jsonObject = JSONObject.parseObject(json);
        String token = request.getHeader("X-Token");// 从 http 请求头中取出 token
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);
        String open_id = map.get("open_id");
        UserAuth userAuth = userService.getUserAuthByWX(open_id);

        UserInfo userInfo = userService.getUserInfo(userAuth.getUid());
        try {
            Comment comment = new Comment();

            comment.setUid(userAuth.getUid());
            comment.setName(userInfo.getNick());
            comment.setAvatar(userInfo.getAvatar());
            comment.setObject_id(object_id);
            comment.setObject_type(object_type);

            Date date = new Date();
            //设置要获取到什么样的时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //获取String类型的时间
            String timestamp= sdf.format(date);
            comment.setTimestamp(timestamp);

            if(jsonObject.getString("content") != null)
                comment.setContent(jsonObject.getString("content"));
            commentService.createComment(comment);


            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("comment",comment);
            result.setData(data);
            response.setHeader("X-Token",map.get("X-Token"));


        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("An Error In Creating A Comment");
            return  result.getJsonObject();
        }

        return result.getJsonObject();
    }

    @UserLoginToken
    @DeleteMapping("/object_type/{object_type}/object_id/{object_id}/comment/{comment_id}")
    public Object deleteComment(@PathVariable("object_type") String object_type, @PathVariable("object_id") int object_id,
                                @PathVariable("comment_id") int comment_id, HttpServletRequest request, HttpServletResponse response){
        JsonUtils result = new JsonUtils();

        String token = request.getHeader("X-Token");// 从 http 请求头中取出 token
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);


        try {

            commentService.cancelComment(comment_id);

            response.setHeader("X-Token",map.get("X-Token"));


        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("An Error In Deleting A Record_list");
            return  result.getJsonObject();
        }

        return result.getJsonObject();
    }

    @UserLoginToken
    @PostMapping("/object_type/{object_type}/object_id/{object_id}/like")
    public Object createLike(@PathVariable("object_type") String object_type, @PathVariable("object_id") int object_id,
                             HttpServletRequest request, HttpServletResponse response){
        JsonUtils result = new JsonUtils();
        String token = request.getHeader("X-Token");// 从 http 请求头中取出 token
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

            response.setHeader("X-Token",map.get("X-Token"));


        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("An Error In Liking a " + object_type);
            return  result.getJsonObject();
        }


        return result.getJsonObject();
    }

    @UserLoginToken
    @DeleteMapping("/object_type/{object_type}/object_id/{object_id}/like")
    public Object cancelLike(@PathVariable("object_type") String object_type, @PathVariable("object_id") int object_id,
                             HttpServletRequest request, HttpServletResponse response){
        JsonUtils result = new JsonUtils();
        String token = request.getHeader("X-Token");// 从 http 请求头中取出 token
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

            response.setHeader("X-Token",map.get("X-Token"));


        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("An Error In Liking  a " + object_type);
            return  result.getJsonObject();
        }


        return result.getJsonObject();
    }



    @UserLoginToken
    @GetMapping("/star_list/object_type/{object_type}")
    public Object getStarList(@PathVariable("object_type") String object_type,HttpServletRequest request,HttpServletResponse response){

        JsonUtils result = new JsonUtils();
        String token = request.getHeader("X-Token");// 从 http 请求头中取出 token
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);
        String open_id = map.get("open_id");
        UserAuth userAuth = userService.getUserAuthByWX(open_id);

        try {
            List<Star> starList;

            if(object_type.equals("collection")){
                starList = starService.getStarListByCollection(userAuth.getUid(),object_type);
                System.out.println("query collection");
            }
            else if(object_type.equals("show")){
                starList = starService.getStarListByShow(userAuth.getUid(),object_type);
                System.out.println("query show");
            }
            else if(object_type.equals("exhibition")) {
                starList = starService.getStarListByExhibition(userAuth.getUid(), object_type);
                System.out.println("query exhibition");
            }
            else {
                result.setStatus("500");
                result.setMsg(" the object_type " + object_type + "is wrong");
                return  result.getJsonObject();
            }
            LinkedHashMap data = new LinkedHashMap<String,Object>();

            data.put("item_list",starList);
            result.setData(data);
            response.setHeader("X-Token",map.get("X-Token"));

        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("An Error In Getting A Star_list of" + object_type);
            return  result.getJsonObject();
        }

        return result.getJsonObject();
    }

    @UserLoginToken
    @GetMapping("/object_type/{object_type}/object_id/{object_id}/star")
    public Object getStar(@PathVariable("object_type") String object_type, @PathVariable("object_id") int object_id,
                             HttpServletRequest request, HttpServletResponse response){
        JsonUtils result = new JsonUtils();

        String token = request.getHeader("X-Token");// 从 http 请求头中取出 token
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);
        String open_id = map.get("open_id");
        UserAuth userAuth = userService.getUserAuthByWX(open_id);
        Star star = null;

        try {
            star = starService.getStarByObject(userAuth.getUid(),object_id,object_type);

            LinkedHashMap data = new LinkedHashMap<String,Object>();

            data.put("star",star);

            if(star != null)
                data.put("star_status",1);
            else
                data.put("star_status",0);
            result.setData(data);

            response.setHeader("X-Token",map.get("X-Token"));


        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("An Error in Getting Star a " + object_type);
            return  result.getJsonObject();
        }


        return result.getJsonObject();
    }

    @UserLoginToken
    @PostMapping("/object_type/{object_type}/object_id/{object_id}/star")
    public Object createStar(@PathVariable("object_type") String object_type, @PathVariable("object_id") int object_id,
                             HttpServletRequest request, HttpServletResponse response){
        JsonUtils result = new JsonUtils();
        Star star = new Star();
        String token = request.getHeader("X-Token");// 从 http 请求头中取出 token
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);
        String open_id = map.get("open_id");
        UserAuth userAuth = userService.getUserAuthByWX(open_id);

        try {

            star = starService.getStarByObject(userAuth.getUid(),object_id,object_type);
            if(star != null){

                result.setStatus("500");
                result.setMsg("the "+object_type+" was Stared");
            }
            else
                star =null;
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

            response.setHeader("X-Token",map.get("X-Token"));


        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("An Error In Staring a " + object_type);
            return  result.getJsonObject();
        }


        return result.getJsonObject();
    }

    @UserLoginToken
    @DeleteMapping("/object_type/{object_type}/object_id/{object_id}/star/{star_id}")
    public Object cancelStar(@PathVariable("object_type") String object_type, @PathVariable("object_id") int object_id,
                             @PathVariable("star_id") int star_id,HttpServletRequest request, HttpServletResponse response){
        JsonUtils result = new JsonUtils();

        String token = request.getHeader("X-Token");// 从 http 请求头中取出 token
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

            response.setHeader("X-Token",map.get("X-Token"));


        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("An Error In Staring a " + object_type);
            return  result.getJsonObject();
        }


        return result.getJsonObject();
    }


    @UserLoginToken
    @PostMapping("/object_type/{object_type}/object_id/{object_id}/report")
    public Object createReport(@RequestBody String json,@PathVariable("object_type") String object_type, @PathVariable("object_id") int object_id,
                             HttpServletRequest request, HttpServletResponse response){
        JsonUtils result = new JsonUtils();
        JSONObject jsonObject = JSONObject.parseObject(json);

        Report report = new Report();
        String token = request.getHeader("X-Token");// 从 http 请求头中取出 token
        LinkedHashMap<String,String> map = tokenService.verifyToken(token);
        String open_id = map.get("open_id");
        UserAuth userAuth = userService.getUserAuthByWX(open_id);

        try {

            Date date = new Date();
            //设置要获取到什么样的时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //获取String类型的时间
            String  timestamp= sdf.format(date);
            report.setTimestamp(timestamp);

            if(jsonObject.getString("type") != null)
                report.setType(jsonObject.getString("type"));
            if(jsonObject.getString("content") != null)
                report.setContent(jsonObject.getString("content"));
            report.setUid(userAuth.getUid());
            report.setObject_id(object_id);
            report.setObject_type(object_type);

            reportService.createReport(report);


            LinkedHashMap data = new LinkedHashMap<String,Object>();
            data.put("user_id", userAuth.getUid());

            data.put("report",report);
            result.setData(data);

            response.setHeader("X-Token",map.get("X-Token"));


        } catch (Exception e) {

            e.printStackTrace();
            result.setStatus("500");
            result.setMsg("An Error In Reporting a " + object_type);
            return  result.getJsonObject();
        }


        return result.getJsonObject();
    }


    public Boolean deleteAllThingByObject(int uid, int object_id ,String Object_type){

        try {

            commentService.cancelCommentByObject(uid, object_id, Object_type);
            recordService.cancelRecordByObject(uid, object_id, Object_type);
            reportService.cancelReportByObject(uid, object_id, Object_type);
            starService.cancelStarByObject(uid, object_id, Object_type);

            return  true;

        }catch (Exception e){
            e.printStackTrace();
        }


        return false;
    }
}
