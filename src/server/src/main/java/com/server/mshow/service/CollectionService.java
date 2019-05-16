package com.server.mshow.service;

import com.server.mshow.domain.Collection;

import java.util.List;

public interface CollectionService {

    Collection getCollection(int cid);
    List<Collection> getAllCollectionList();
    List <Collection> getCollectionListBySid(int sid);
    void createCollection(Collection Collection);
    void updateCollection(Collection Collection);
    void deleteCollection(int cid);
}
