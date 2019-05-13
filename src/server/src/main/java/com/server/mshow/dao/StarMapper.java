package com.server.mshow.dao;

import com.server.mshow.domain.Star;

import java.util.List;

public interface StarMapper {

    Star getStar(String sid);
    List<Star> getStarByUser(String uid);
    void createStar(Star star);
    void cancelStar(String sid);//delete 删除收藏记录
}
