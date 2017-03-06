package com.boka.cloud.controller;

import com.boka.cloud.annotation.Auth;
import com.boka.cloud.dto.ResultTO;
import com.boka.cloud.service.RecordService;
import com.boka.cloud.service.UserService;
import com.boka.cloud.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController extends BaseController {

    @Resource
    private RecordService recordService;
    @Resource
    private UserService userService;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }


    @RequestMapping("/records")
    @Auth(required = true)
    ResultTO records() {
        return sendResult(recordService.getAll());
    }

    @RequestMapping("/users")
    ResultTO users(@RequestParam String keyword) {
        if(Assert.isNotNull(keyword)) {
            return sendResult(userService.getByKeyword(keyword));
        }
        return sendResult(userService.getAll());
    }

}