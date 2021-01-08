package com.sixtofly.auth.common.config;

import com.sixtofly.auth.common.constants.CommonConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * 公共配置类
 * @author xie yuan bing
 * @date 2019-12-11 16:20
 * @description
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class AuthCommonAutoConfiguration {


    /**
     * 加密方式
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Redis存储Token
     */
    @Bean
    public TokenStore tokenStore(RedisConnectionFactory connectionFactory){
        TokenStore tokenStore = new RedisTokenStore(connectionFactory);
        return tokenStore;
    }


    @Bean
    public ClientDetailsService clientDetailsService(DataSource dataSource){
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * JWT方式
     * @return
     */
//    @Bean
//    public TokenStore tokenStore(){
//        return new JwtTokenStore(jwtAccessTokenConverter());
//    }
//
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey(CommonConstants.JWT_SIGN_KEY);
//        converter.setAccessTokenConverter(new SecurityAccessTokenConverter());
//        return converter;
//    }
}
