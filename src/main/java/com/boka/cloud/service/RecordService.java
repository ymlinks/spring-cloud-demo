package com.boka.cloud.service;


import com.boka.cloud.model.Record;

import java.util.List;

/**
 * Created by boka on 14-12-18.
 */
public interface RecordService {

    void saveRecord(Record record);

    List<Record> getAll();
}
