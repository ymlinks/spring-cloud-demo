package com.ymlinks.cloud.controller;

import com.ymlinks.cloud.annotation.Auth;
import com.ymlinks.cloud.dto.ResultTO;
import com.ymlinks.cloud.service.RecordService;
import com.ymlinks.cloud.service.UserService;
import com.ymlinks.cloud.util.Assert;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController extends BaseController {

    @Resource
    private RecordService recordService;
    @Resource
    private UserService userService;

    @GetMapping
    @ApiOperation("首页")
    public String home() {
        return "Hello World!";
    }

    @GetMapping("/records")
    @Auth(required = true)
    @ApiOperation(value = "记录列表", notes = "这种类型的查询是精确查询,其结果只有一条数据", response = ResultTO.class)
    ResultTO records() {
        return sendResult(recordService.getAll());
    }

    @GetMapping("/users")
    @ApiOperation(value = "用户列表", notes = "这种类型的查询是精确查询,其结果只有一条数据", response = ResultTO.class)
    public ResultTO users(@RequestParam(required = false) String keyword) {
        if (Assert.isNotNull(keyword)) {
            return sendResult(userService.getByKeyword(keyword));
        }
        return sendResult(userService.getAll());
    }

}