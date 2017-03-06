package com.boka.cloud.service.impl;

import com.boka.cloud.mapper.RecordMapper;
import com.boka.cloud.model.Record;
import com.boka.cloud.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by boka on 14-12-18.
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS, rollbackFor = Throwable.class)
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Transactional
    @Override
    public void saveRecord(Record record) {
        recordMapper.saveRecord(record);
    }

    @Override
    public List<Record> getAll() {
        return recordMapper.findAll();
    }

}
