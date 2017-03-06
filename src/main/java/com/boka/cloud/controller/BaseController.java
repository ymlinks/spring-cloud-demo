package com.boka.cloud.controller;

import com.boka.cloud.dto.ResultTO;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by FuZhaohui on 2016/2/23.
 */
@CrossOrigin(origins = "*.bokao2o.com")
public abstract class BaseController {

    public <T> ResultTO<T> sendResult(T data) {
        ResultTO<T> result = new ResultTO<>();
        result.setResult(data);
        return result;
    }

}
