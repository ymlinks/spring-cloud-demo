package com.ymlinks.common.controller;

import com.ymlinks.common.dto.ResultTO;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by FuZhaohui on 2016/2/23.
 */
@RefreshScope
@CrossOrigin("*")
public abstract class BaseController {

    public <T> ResultTO<T> sendResult(T data) {
        ResultTO<T> result = new ResultTO<>();
        result.setResult(data);
        return result;
    }

}
