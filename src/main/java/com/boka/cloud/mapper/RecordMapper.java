package com.boka.cloud.mapper;


import com.boka.cloud.model.Record;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by boka on 14-12-18.
 */
@Mapper
public interface RecordMapper {

    void saveRecord(Record record);

    List<Record> findAll();

}
