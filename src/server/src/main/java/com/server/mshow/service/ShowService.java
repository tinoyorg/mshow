package com.server.mshow.service;

import com.server.mshow.domain.Show;

import java.util.List;

public interface ShowService {

    Show getShow(String sid);
    List<Show> getAllShowList();
    List <Show> getShowListByeid(String eid);
    void createShow(Show show);
    void updateShow(Show show);
    void deleteShow(String sid);
}
