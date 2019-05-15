package com.server.mshow.dao;

import com.server.mshow.domain.Show;

import java.util.List;

public interface ShowMapper {
    //展览查询

    Show getShow(int sid);
    List<Show> getAllShowList();
    List <Show> getShowListByEid(int eid);
    void createShow(Show show);
    void updateShow(Show show);
    void deleteShow(int sid);

}
