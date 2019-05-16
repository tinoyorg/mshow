package com.server.mshow.dao;

import com.server.mshow.domain.Exhibition;

import java.util.List;

public interface ExhibitionMapper {
    //展馆查询
    
    Exhibition getExhibition(int eid);
    List<Exhibition> getAllExhiblitionList();
    List <Exhibition> getExhibitionListByUid(int uid);
    void createExhibition(Exhibition exhibition);
    void updateExhibition(Exhibition exhibition);
    void deleteExhibition(int eid);
}
