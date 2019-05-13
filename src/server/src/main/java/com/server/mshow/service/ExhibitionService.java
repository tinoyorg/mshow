package com.server.mshow.service;

import com.server.mshow.domain.Exhibition;

import java.util.List;

public interface ExhibitionService {

    Exhibition getExhibition(String eid);
    List <Exhibition> getAllExhibitionList();
    List <Exhibition> getExhibitionListByUid(String uid);
    void createExhibition(Exhibition exhibition);
    void updateExhibition(Exhibition exhibition);
    void deleteExhibition(String eid);

}
