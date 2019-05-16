package com.server.mshow.service.impl;

import com.server.mshow.dao.ExhibitionMapper;
import com.server.mshow.domain.Exhibition;
import com.server.mshow.service.ExhibitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ExhibitionService")
public class ExhibitionServiceImpl implements ExhibitionService {

    @Autowired
    private ExhibitionMapper exhibitionMapper;

    @Override
    public Exhibition getExhibition(int eid) {
        return exhibitionMapper.getExhibition(eid);
    }

    @Override
    public List<Exhibition> getAllExhibitionList() {
        return exhibitionMapper.getAllExhibitionList();
    }

    @Override
    public List<Exhibition> getExhibitionListByUid(int uid) {
        return exhibitionMapper.getExhibitionListByUid(uid);
    }

    @Override
    public void createExhibition(Exhibition exhibition) {
        exhibitionMapper.createExhibition(exhibition);
    }

    @Override
    public void updateExhibition(Exhibition exhibition) {
        exhibitionMapper.updateExhibition(exhibition);
    }

    @Override
    public void deleteExhibition(int eid) {
        exhibitionMapper.deleteExhibition(eid);
    }
}
