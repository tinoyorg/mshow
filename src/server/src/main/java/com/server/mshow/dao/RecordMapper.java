package com.server.mshow.dao;

import com.server.mshow.domain.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface RecordMapper {

    Record getRecord(int rid);
    List<Record> getRecordByUser(int uid);
    void createRecord(Record record);
    void cancelRecord(int rid);//delete 删除某条浏览记录
    void cancelAllRecordByUid(int uid);//delete 删除用户所有浏览记录
    void cancelRecordByObject(@Param("uid") int uid, @Param("object_id") int object_id, @Param("object_type") String object_type);

}
