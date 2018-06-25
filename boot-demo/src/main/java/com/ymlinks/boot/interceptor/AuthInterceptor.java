package com.ymlinks.boot.interceptor;

import com.ymlinks.boot.annotation.Auth;
import com.ymlinks.boot.util.AuthUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Admin on 2016/1/26 0026.
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private AuthUtil authUtil;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Auth auth = method.getAnnotation(Auth.class);
        if (auth != null) {
            Map<String, String> authMap;
            if (auth.required()) {
                authMap = authUtil.auth(request);
                if (authMap == null) {
                    return false;
                }
            } else {
                authMap = authUtil.preAuth(request);
                if (authMap == null) {
                    return false;
                }
            }
            request.setAttribute("authMap", authMap);
        }
        return true;
    }
}
