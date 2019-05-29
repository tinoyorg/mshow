package com.server.mshow.service.impl;

import com.server.mshow.dao.ToolMapper;
import com.server.mshow.domain.Collection;
import com.server.mshow.domain.Exhibition;
import com.server.mshow.domain.Show;
import com.server.mshow.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ToolService")
public class ToolServiceImpl implements ToolService {

    @Autowired
    ToolMapper toolMapper;

    @Override
    public List<Collection> searchCollection(String queryStr) {
        return toolMapper.searchCollection(queryStr);
    }

    @Override
    public List<Show> searchShow(String queryStr) {
        return toolMapper.searchShow(queryStr);
    }

    @Override
    public List<Exhibition> searchExhibition(String queryStr) {
        return toolMapper.searchExhibition(queryStr);
    }
}
