package com.server.mshow.dao;

import com.server.mshow.domain.Collection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CollectionMapper {
    //展品查询
    
    Collection getCollection(int cid);//获取某一展品信息
    List<Collection> getAllCollectionList();//获取
    List <Collection> getCollectionListBySid(int sid);//获取某展览下所有展品
    void createCollection(Collection Collection);//创建展品
    void updateCollection(Collection Collection);//更改展品信息
    void deleteCollection(int cid);//删除展品

}
