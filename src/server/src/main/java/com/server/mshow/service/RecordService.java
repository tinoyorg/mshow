package com.server.mshow.service;

import com.server.mshow.domain.Record;

import java.util.List;

public interface RecordService {

    Record getRecord(String rid);
    List<Record> getRecordByUser(String uid);
    void createRecord(Record record);
    void cancelRecord(String rid);//delete 删除某条浏览记录
    void cancelAllRecordByUid(String uid);//delete 删除用户所有浏览记录


}
