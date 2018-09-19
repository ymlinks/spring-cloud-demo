package com.ymlinks.common.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ymlinks.common.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Resource
    private HttpUtil httpUtil;

    /**
     * 获取注解中对方法的描述信息
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public String[] getMethodDescription(JoinPoint joinPoint) {
        String[] functionInfo = new String[7];
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        Map<String, String[]> paramsMap = request.getParameterMap();
        String product = request.getHeader("product");
        functionInfo[0] = product;
        functionInfo[4] = request.getHeader("token");
        functionInfo[6] = request.getHeader("User-Agent");
        functionInfo[1] = httpUtil.getIp(request);
        MethodSignature methodSignature = (MethodSignature) joinPoint.getStaticPart().getSignature();
        Method method = methodSignature.getMethod();
        Object[] args = joinPoint.getArgs();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        assert args.length == parameterAnnotations.length;
        for (int argIndex = 0; argIndex < args.length; argIndex++) {
            for (Annotation annotation : parameterAnnotations[argIndex]) {
                if (annotation instanceof PathVariable) {
                    if (args[argIndex] != null) {
                        if (StringUtils.isEmpty(functionInfo[5])) {
                            functionInfo[5] = "";
                        }
                        functionInfo[5] += args[argIndex] + "&";
                    }
                }
                if (annotation instanceof RequestBody) {
                    functionInfo[3] = JSON.toJSONString(args[argIndex], SerializerFeature.DisableCircularReferenceDetect);
                }
            }
        }
        StringBuilder params = new StringBuilder();
        if (paramsMap != null && paramsMap.size() > 0) {
            for (Map.Entry<String, String[]> entry : paramsMap.entrySet()) {
                String[] values = entry.getValue();
                if (values != null) {
                    String value;
                    if (values.length == 1) {
                        value = values[0];
                    } else {
                        value = JSON.toJSONString(values, SerializerFeature.DisableCircularReferenceDetect);
                    }
                    params.append(entry.getKey() + "=" + value + "&");
                }
            }
        }
        functionInfo[2] = params.toString();
        return functionInfo;
    }

    //Service层切点
    @Pointcut("execution(* com.ymlinks.*.service.*.*(..))")
    public void serviceAspect() {
    }

    //Controller层切点
    @Pointcut("execution(* com.ymlinks.*.controller.*.*(..))")
    public void controllerAspect() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("######################### INFO #########################");
        String[] controllerInfo = getMethodDescription(joinPoint);
        String function = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()";
        log.info("请求方法: " + function);
        log.info("请求认证: token=" + controllerInfo[4]);
        if (!StringUtils.isEmpty(controllerInfo[5])) {
            log.info("请求参数1: " + controllerInfo[5]);
        }
        if (!StringUtils.isEmpty(controllerInfo[2])) {
            log.info("请求参数2: " + controllerInfo[2]);
        }
        if (!StringUtils.isEmpty(controllerInfo[3])) {
            log.info("请求参数3: " + controllerInfo[3]);
        }
        log.info("请求来源: " + controllerInfo[0]);
        log.info("User-Agent: " + controllerInfo[6]);
        log.info("请求IP: " + controllerInfo[1] + "\n");
    }

    /**
     * 异常通知 用于拦截service层记录异常日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        try {
            log.error("========================= Error =========================");
            String[] functionInfo = getMethodDescription(joinPoint);
            String method = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()";
            log.error("异常方法:" + method);
            log.error("异常信息:" + e.getMessage());
            log.error("请求认证: token=" + functionInfo[4]);
            if (!StringUtils.isEmpty(functionInfo[5])) {
                log.error("请求参数1: " + functionInfo[5]);
            }
            if (!StringUtils.isEmpty(functionInfo[2])) {
                log.error("请求参数2: " + functionInfo[2]);
            }
            if (!StringUtils.isEmpty(functionInfo[3])) {
                log.error("请求参数3: " + functionInfo[3]);
            }
            log.error("请求来源: " + functionInfo[0]);
            log.error("User-Agent: " + functionInfo[6]);
            log.error("请求IP: " + functionInfo[1] + "\n");
        } catch (Exception ex) {
            log.error("异常拦截异常", ex);
        }
    }
} 