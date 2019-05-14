package com.server.mshow.service.impl;

import com.server.mshow.dao.ExhibitionMapper;
import com.server.mshow.domain.Exhibition;
import com.server.mshow.service.ExhibitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ExhibitionService")
public class ExhibitionServiceImpl implements ExhibitionService {

    //@Autowired
    private ExhibitionMapper exhibitionMapper;

    @Override
    public Exhibition getExhibition(String eid) {
        return null;
    }

    @Override
    public List<Exhibition> getAllExhibitionList() {
        return null;
    }

    @Override
    public List<Exhibition> getExhibitionListByUid(String uid) {
        return null;
    }

    @Override
    public void createExhibition(Exhibition exhibition) {

    }

    @Override
    public void updateExhibition(Exhibition exhibition) {

    }

    @Override
    public void deleteExhibition(String eid) {

    }
}
