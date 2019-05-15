package com.server.mshow.dao;

import com.server.mshow.domain.Record;

import java.util.List;

public interface RecordMapper {

    Record getRecord(int rid);
    List<Record> getRecordByUser(int uid);
    void createRecord(Record record);
    void cancelRecord(int rid);//delete 删除某条浏览记录
    void cancelAllRecordByUid(int uid);//delete 删除用户所有浏览记录
}
