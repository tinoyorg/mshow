package com.server.mshow.service;

import com.server.mshow.domain.Collection;

import java.util.List;

public interface CollectionService {

    Collection getCollection(String cid);
    List<Collection> getAllCollectionList();
    List <Collection> getCollectionBysid(String sid);
    void createCollection(Collection Collection);
    void updateCollection(Collection Collection);
    void deleteCollection(String cid);
}
