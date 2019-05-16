package com.server.mshow.service.impl;

import com.server.mshow.dao.CollectionMapper;
import com.server.mshow.domain.Collection;
import com.server.mshow.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CollectionService")
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public Collection getCollection(int cid) {
        return collectionMapper.getCollection(cid);
    }

    @Override
    public List<Collection> getAllCollectionList() {
        return collectionMapper.getAllCollectionList();
    }

    @Override
    public List<Collection> getCollectionListBySid(int sid) {
        return collectionMapper.getCollectionListBySid(sid);
    }

    @Override
    public void createCollection(Collection collection) {
        collectionMapper.createCollection(collection);
    }

    @Override
    public void updateCollection(Collection collection) {
        collectionMapper.updateCollection(collection);
    }

    @Override
    public void deleteCollection(int cid) {
        collectionMapper.deleteCollection(cid);
    }
}
