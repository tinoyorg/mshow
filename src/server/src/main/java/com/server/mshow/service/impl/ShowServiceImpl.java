package com.server.mshow.service.impl;



import com.server.mshow.dao.ShowMapper;
import com.server.mshow.domain.Show;
import com.server.mshow.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShowServiceImpl implements ShowService {

    //@Autowired
    private ShowMapper showMapper;

    @Override
    public Show getShow(String sid) {
        return null;
    }

    @Override
    public List<Show> getAllShowList() {
        return null;
    }

    @Override
    public List<Show> getShowListByeid(String eid) {
        return null;
    }

    @Override
    public void createShow(Show show) {

    }

    @Override
    public void updateShow(Show show) {

    }

    @Override
    public void deleteShow(String sid) {

    }
}
