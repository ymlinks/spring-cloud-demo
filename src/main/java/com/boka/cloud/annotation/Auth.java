package com.boka.cloud.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Admin on 2016/1/26 0026.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
    boolean required() default false; // 是否需要认证

    boolean isApp() default true; // 是否为App模式

    boolean mobile() default false;
}
