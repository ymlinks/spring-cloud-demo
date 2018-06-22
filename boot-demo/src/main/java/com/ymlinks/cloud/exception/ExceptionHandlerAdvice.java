package com.ymlinks.cloud.exception;

import com.alibaba.fastjson.JSONException;
import com.ymlinks.cloud.dto.ResultTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Admin on 2016/1/14 0014.
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice {


    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultTO exceptionResponse(Exception e) {
        ResultTO result = new ResultTO();
        result.setSuccess(false);
        result.setCode(500);
        e.printStackTrace();
        result.setMsg("服务器错误");
        log.error(e.getMessage());
        return result;
    }

    @ExceptionHandler(value = LoginException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultTO loginResponse(LoginException le) {
        ResultTO result = new ResultTO();
        result.setSuccess(false);
        result.setCode(401);
        result.setMsg(le.getMessage());
        return result;
    }

    @ExceptionHandler(value = PollingException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultTO pollingResponse(PollingException pe) {
        ResultTO result = new ResultTO();
        result.setSuccess(false);
        result.setCode(205);
        result.setMsg(pe.getMessage());
        return result;
    }

    @ExceptionHandler(value = AuthException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultTO authResponse(AuthException ae) {
        ResultTO result = new ResultTO();
        result.setSuccess(false);
        result.setCode(403);
        result.setMsg(ae.getMessage());
        return result;
    }

    @ExceptionHandler(value = CommonException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultTO commonResponse(CommonException ce) {
        ResultTO result = new ResultTO();
        result.setSuccess(false);
        result.setCode(ce.getCode());
        result.setMsg(ce.getMessage());
        return result;
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultTO httpRequestMethodResponse(HttpRequestMethodNotSupportedException hrmse) {
        ResultTO result = new ResultTO();
        result.setSuccess(false);
        result.setCode(405);
        result.setMsg(hrmse.getMessage());
        return result;
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultTO httpMessageNotReadableResponse(HttpMessageNotReadableException hmnre) {
        ResultTO result = new ResultTO();
        result.setSuccess(false);
        result.setCode(400);
        result.setMsg(hmnre.getMessage());
        return result;
    }

    @ExceptionHandler(value = JSONException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultTO httpMessageNotReadableResponse(JSONException je) {
        ResultTO result = new ResultTO();
        result.setSuccess(false);
        result.setCode(400);
        result.setMsg(je.getMessage());
        return result;
    }
}
