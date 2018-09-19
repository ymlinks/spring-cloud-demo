package com.ymlinks.cloud.controller;

import com.ymlinks.cloud.service.OssService;
import com.ymlinks.common.controller.BaseController;
import com.ymlinks.common.dto.ResultTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping
@RestController
public class FileController extends BaseController {

    @Resource
    private OssService ossService;

    @GetMapping("/token")
    public ResultTO getToken() {
        return sendResult(ossService.getToken());
    }

}
