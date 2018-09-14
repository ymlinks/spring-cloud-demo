package com.ymlinks.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@Configuration
@EnableConfigurationProperties({
        SwaggerProperties.class
})
public class Swagger2Config {

    @Value("${app.name}")
    private String appName;

    @Bean
    public Docket createRestApi() {
        List<Parameter> params = new ArrayList<>();
        ParameterBuilder param = new ParameterBuilder();
        param.name("token").modelRef(new ModelRef("string")).parameterType("header");
        params.add(param.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(params)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(String.format("com.ymlinks.%s.controller", appName)))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfoBuilder().title("禾维科技 Spring Boot Platform API")
                .description("The APIs here demonstrate creating a service built with Spring Boot")
                .license("MIT")
                .licenseUrl("http://opensource.org/licenses/MIT")
                .contact(new Contact("ShFu", "www.ymlinks.com", "fuzhaohui200@gmail.com"))
                .version("1.0")
                .build();

        return apiInfo;
    }
}