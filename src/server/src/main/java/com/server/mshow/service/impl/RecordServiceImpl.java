package com.server.mshow.service.impl;

import com.server.mshow.dao.RecordMapper;
import com.server.mshow.domain.Record;
import com.server.mshow.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("RecordService")
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public Record getRecord(int rid) {
        return recordMapper.getRecord(rid);
    }

    @Override
    public List<Record> getRecordByUser(int uid) {
        return getRecordByUser(uid);
    }

    @Override
    public void createRecord(Record record) {
        recordMapper.createRecord(record);
    }

    @Override
    public void cancelRecord(int rid) {
        recordMapper.cancelRecord(rid);
    }

    @Override
    public void cancelAllRecordByUid(int uid) {
        recordMapper.cancelAllRecordByUid(uid);
    }

    @Override
    public void cancelRecordByObject(int uid, int object_id, String object_type) {
        recordMapper.cancelRecordByObject(uid, object_id, object_type);
    }

}
