package com.server.mshow.dao;

import com.server.mshow.domain.Collection;

import java.util.List;

public interface CollectionMapper {

    Collection getCollection(int cid);
    List<Collection> getAllCollectionList();
    List <Collection> getCollectionBysid(int sid);
    void createCollection(Collection Collection);
    void updateCollection(Collection Collection);
    void deleteCollection(int cid);

}
