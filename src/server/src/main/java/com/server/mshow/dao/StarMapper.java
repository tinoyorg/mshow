package com.server.mshow.dao;

import com.server.mshow.domain.Star;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface StarMapper {

    Star getStar(int sid);
    List<Star> getStarByUser(int uid);
    void createStar(Star star);
    void cancelStar(int sid);//delete 删除收藏记录
}
