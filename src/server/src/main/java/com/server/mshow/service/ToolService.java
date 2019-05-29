package com.server.mshow.service;

import com.server.mshow.domain.Collection;
import com.server.mshow.domain.Exhibition;
import com.server.mshow.domain.Show;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ToolService {

    List<Collection> searchCollection(String queryStr);
    List<Show> searchShow(String queryStr);
    List<Exhibition> searchExhibition(String queryStr);

}
