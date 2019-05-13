package com.server.mshow.dao;

import com.server.mshow.domain.Show;

import java.util.List;

public interface ShowMapper {

    Show getShow(String sid);
    List<Show> getAllShowList();
    List <Show> getShowListByeid(String eid);
    void createShow(Show show);
    void updateShow(Show show);
    void deleteShow(String sid);

}
