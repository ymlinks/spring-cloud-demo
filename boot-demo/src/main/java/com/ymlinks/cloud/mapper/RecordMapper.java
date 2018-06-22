package com.ymlinks.cloud.mapper;


import com.ymlinks.cloud.model.Record;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by ymlinks on 14-12-18.
 */
@Mapper
public interface RecordMapper {

    void saveRecord(Record record);

    List<Record> findAll();

}
