package com.server.mshow.dao;

import com.server.mshow.domain.Collection;
import com.server.mshow.domain.Exhibition;
import com.server.mshow.domain.Show;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ToolMapper {
    List<Collection> searchCollection(String queryStr);
    List<Show> searchShow(String queryStr);
    List<Exhibition> searchExhibition(String queryStr);

}
