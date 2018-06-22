package com.ymlinks.cloud;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Slf4j
@EnableAutoConfiguration
@EnableWebMvc
@EnableCaching
@ComponentScan("com.ymlinks")
@MapperScan("com.ymlinks.cloud.mapper")
@EnableMongoRepositories("com.ymlinks.*.repository")
public class BootApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

    @Autowired
    void setEnvironment(Environment env) {
        log.info("loading env config app name: {}", env.getProperty("app.name"));
    }
}