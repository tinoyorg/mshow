package com.server.mshow.service;

import com.server.mshow.domain.Star;

import java.util.List;

public interface StarService {

    Star getStar(int sid);
    Star getStarByObject(int uid,int object_id,String object_type);
    List<Star> getStarByUser(int uid);
    List<Star> getStarListByExhibition(int uid,String object_type);
    List<Star> getStarListByShow(int uid,String object_type);
    List<Star> getStarListByCollection(int uid,String object_type);
    void createStar(Star star);
    void cancelStar(int sid);//delete 删除收藏记录
    void cancelStarByObject(int uid,int object_id,String object_type);

}
