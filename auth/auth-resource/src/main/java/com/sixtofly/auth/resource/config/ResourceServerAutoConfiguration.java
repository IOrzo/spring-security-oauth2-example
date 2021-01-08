package com.sixtofly.auth.resource.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.*;

/**
 * 资源服务器自动配置类
 * @author xie yuan bing
 * @date 2019-12-16 11:45
 * @description
 */
@Configuration
@EnableResourceServer
public class ResourceServerAutoConfiguration {

    @Bean
    public ResourceServerConfigurer resourceServerConfigurer(ResourceServerTokenServices tokenServices){
        return new SecurityResourceServerConfigurer.Builder()
                .tokenServices(tokenServices)
//                .expressionHandler(expressionHandler)
                .build();
    }

    @Bean
    public ResourceServerTokenServices defaultTokenServices(TokenStore tokenStore, ClientDetailsService clientDetailsService){
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setTokenStore(tokenStore);
//        tokenServices.setClientDetailsService(clientDetailsService);

        RemoteTokenServices tokenServices = new RemoteTokenServices();
        tokenServices.setCheckTokenEndpointUrl("http://127.0.0.1:8812/oauth/check_token");
        tokenServices.setClientId("web");
        tokenServices.setClientSecret("web");

        // 配置Token转化器, 获取自定义信息
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        SecurityUserAuthenticationConverter userAuthenticationConverter = new SecurityUserAuthenticationConverter();
        accessTokenConverter.setUserTokenConverter(userAuthenticationConverter);
        tokenServices.setAccessTokenConverter(accessTokenConverter);

        return tokenServices;
    }

    /**
     * 配置oauth2 方法拦截表达式
     * TODO 失败
     */
//    @Bean
//    public OAuth2WebSecurityExpressionHandler expressionHandler(ApplicationContext applicationContext) {
//        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
//        expressionHandler.setApplicationContext(applicationContext);
//        return expressionHandler;
//    }
}
