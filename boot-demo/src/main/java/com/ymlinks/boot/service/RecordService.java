package com.ymlinks.boot.service;


import com.ymlinks.boot.model.Record;

import java.util.List;

/**
 * Created by ymlinks on 14-12-18.
 */
public interface RecordService {

    void saveRecord(Record record);

    List<Record> getAll();
}
