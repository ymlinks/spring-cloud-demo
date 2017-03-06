package com.boka.cloud.controller;

import com.boka.cloud.model.Record;
import com.boka.cloud.service.RecordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {

    @Resource
    private RecordService recordService;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }


    @RequestMapping("/records")
    @ResponseBody
    List<Record> records() {
        return recordService.getAll();
    }

}