package com.ymlinks.common.annotation;

import java.lang.annotation.*;

/**
 * Created by Admin on 2016/1/26 0026.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    String service() default "user"; // 服务
    String desc() default ""; // 日志内容

}
