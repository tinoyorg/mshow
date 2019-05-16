package com.server.mshow.dao;

import com.server.mshow.domain.Exhibition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExhibitionMapper {
    //展馆查询
    
    Exhibition getExhibition(int eid);
    List<Exhibition> getAllExhibitionList();
    List <Exhibition> getExhibitionListByUid(int uid);
    void createExhibition(Exhibition exhibition);
    void updateExhibition(Exhibition exhibition);
    void deleteExhibition(int eid);
}
