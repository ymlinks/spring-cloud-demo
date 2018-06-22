package com.ymlinks.cloud.service;


import com.ymlinks.cloud.model.Record;
import com.ymlinks.cloud.model.Record;

import java.util.List;

/**
 * Created by ymlinks on 14-12-18.
 */
public interface RecordService {

    void saveRecord(Record record);

    List<Record> getAll();
}
