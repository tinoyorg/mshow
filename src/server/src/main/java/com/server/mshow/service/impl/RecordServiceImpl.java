package com.server.mshow.service.impl;

import com.server.mshow.dao.RecordMapper;
import com.server.mshow.domain.Record;
import com.server.mshow.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RecordServiceImpl implements RecordService {

    //@Autowired
    private RecordMapper recordMapper;

    @Override
    public Record getRecord(String rid) {
        return null;
    }

    @Override
    public List<Record> getRecordByUser(String uid) {
        return null;
    }

    @Override
    public void createRecord(Record record) {

    }

    @Override
    public void cancelRecord(String rid) {

    }

    @Override
    public void cancelAllRecordByUid(String uid) {

    }
}
