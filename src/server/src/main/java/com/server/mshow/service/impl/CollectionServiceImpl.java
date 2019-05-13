package com.server.mshow.service.impl;

import com.server.mshow.dao.CollectionMapper;
import com.server.mshow.domain.Collection;
import com.server.mshow.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CollectionServiceImpl implements CollectionService {

    //@Autowired
    private CollectionMapper collectionMapper;

    @Override
    public Collection getCollection(String cid) {
        return null;
    }

    @Override
    public List<Collection> getAllCollectionList() {
        return null;
    }

    @Override
    public List<Collection> getCollectionBysid(String sid) {
        return null;
    }

    @Override
    public void createCollection(Collection Collection) {

    }

    @Override
    public void updateCollection(Collection Collection) {

    }

    @Override
    public void deleteCollection(String cid) {

    }
}
