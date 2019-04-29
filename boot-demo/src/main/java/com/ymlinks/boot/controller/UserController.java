package com.ymlinks.boot.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseController {


    @GetMapping
    @ApiOperation("首页")
    public String home() {
        return "Hello World!";
    }
}