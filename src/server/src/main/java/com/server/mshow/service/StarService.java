package com.server.mshow.service;

import com.server.mshow.domain.Star;

import java.util.List;

public interface StarService {

    Star getStar(int sid);
    List<Star> getStarByUser(int uid);
    void createStar(Star star);
    void cancelStar(int sid);//delete 删除收藏记录


}
