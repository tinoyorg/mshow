package com.server.mshow.service.impl;

import com.server.mshow.dao.StarMapper;
import com.server.mshow.domain.Star;
import com.server.mshow.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("StarService")
public class StarServiceImpl implements StarService {

    @Autowired
    private StarMapper starMapper;

    @Override
    public Star getStar(int sid) {
        return starMapper.getStar(sid);
    }

    @Override
    public Star getStarByObject(int uid, int object_id, String object_type) {
        return starMapper.getStarByObject(uid, object_id, object_type);
    }

    @Override
    public List<Star> getStarByUser(int uid) {
        return starMapper.getStarByUser(uid);
    }

    @Override
    public List<Star> getStarListByExhibition(int uid, String object_type) {
        return starMapper.getStarListByExhibition(uid, object_type);
    }

    @Override
    public List<Star> getStarListByShow(int uid, String object_type) {
        return starMapper.getStarListByShow(uid, object_type);
    }

    @Override
    public List<Star> getStarListByCollection(int uid, String object_type) {
        return starMapper.getStarListByCollection(uid,object_type);
    }


    @Override
    public void createStar(Star star) {
        starMapper.createStar(star);
    }

    @Override
    public void cancelStar(int sid) {
        starMapper.cancelStar(sid);
    }

    @Override
    public void cancelStarByObject(int uid, int object_id, String object_type) {
        starMapper.cancelStarByObject(uid, object_id, object_type);
    }
}
