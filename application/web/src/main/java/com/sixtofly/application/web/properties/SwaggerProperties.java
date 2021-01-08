package com.sixtofly.application.web.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Swagger配置信息
 * @author xie yuan bing
 * @date 2020-01-19 11:37
 * @description
 */
@ConfigurationProperties(prefix = "swagger.basic")
@Data
public class SwaggerProperties {

    private boolean enable;

    private String username;

    private String password;
}
