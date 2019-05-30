package com.server.mshow.dao;

import com.server.mshow.domain.Star;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface StarMapper {

    Star getStar(int sid);
    Star getStarByObject(@Param("uid") int uid, @Param("object_id") int object_id, @Param("object_type") String object_type);
    List<Star> getStarByUser(int uid);
    List<Star> getStarListByExhibition(@Param("uid") int uid,@Param("object_type") String object_type);
    List<Star> getStarListByShow(@Param("uid") int uid,@Param("object_type") String object_type);
    List<Star> getStarListByCollection(@Param("uid") int uid,@Param("object_type") String object_type);
    void createStar(Star star);
    void cancelStar(int sid);//delete 删除收藏记录
    void cancelStarByObject(@Param("uid") int uid, @Param("object_id") int object_id, @Param("object_type") String object_type);
}
