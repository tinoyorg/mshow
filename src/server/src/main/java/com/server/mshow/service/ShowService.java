package com.server.mshow.service;

import com.server.mshow.domain.Show;

import java.util.List;

public interface ShowService {

    Show getShow(int sid);
    List<Show> getAllShowList();
    List <Show> getShowListByEid(int eid);
    void createShow(Show show);
    void updateShow(Show show);
    void deleteShow(int sid);
    void deleteShowByEid(int eid);
}
