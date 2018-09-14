package com.ymlinks.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(SwaggerProperties.PREFIX)
@Data
public class SwaggerProperties {
    public static final String PREFIX = "app.swagger";

    private Boolean enabled = true;

    private String browsingBasePath = "/api/swagger";

    private String invokingBasePath = "";
}