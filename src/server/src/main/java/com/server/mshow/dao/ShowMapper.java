package com.server.mshow.dao;

import com.server.mshow.domain.Show;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShowMapper {
    //展览查询

    Show getShow(int sid);
    List<Show> getAllShowList();
    List <Show> getShowListByEid(int eid);
    void createShow(Show show);
    void updateShow(Show show);
    void deleteShow(int sid);

}
