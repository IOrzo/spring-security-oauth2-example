package com.sixtofly.auth.server.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 服务器本身配置类
 * @author xie yuan bing
 * @date 2019-12-17 18:03
 * @description
 */
@Configuration
public class AuthServerConfig {

    /**
     * 配置HttpMessageConverters
     * @return
     */
//    @Bean
    public HttpMessageConverters httpMessageConverters(){
        return new HttpMessageConverters();
    }

}
