package com.server.mshow.dao;

import com.server.mshow.domain.Collection;

import java.util.List;

public interface CollectionMapper {
    //展品查询
    
    Collection getCollection(int cid);
    List<Collection> getAllCollectionList();
    List <Collection> getCollectionBySid(int sid);
    void createCollection(Collection Collection);
    void updateCollection(Collection Collection);
    void deleteCollection(int cid);

}
