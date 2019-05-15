package com.server.mshow.service.impl;

import com.server.mshow.dao.StarMapper;
import com.server.mshow.domain.Star;
import com.server.mshow.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("StarService")
public class StarServiceImpl implements StarService {

    //@Autowired
    private StarMapper starMapper;

    @Override
    public Star getStar(String sid) {
        return starMapper.getStar(sid);
    }

    @Override
    public List<Star> getStarByUser(String uid) {
        return starMapper.getStarByUser(uid);
    }

    @Override
    public void createStar(Star star) {
        starMapper.createStar(star);
    }

    @Override
    public void cancelStar(String sid) {
        starMapper.cancelStar(sid);
    }
}
