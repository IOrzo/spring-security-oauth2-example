package com.sixtofly.auth.server.config;

import com.sixtofly.auth.common.entity.SecurityUser;
import com.sixtofly.auth.server.config.token.granter.CustomTokenGranter;
import com.sixtofly.auth.server.exception.SecurityExceptionTranslator;
import com.sixtofly.auth.server.service.PhoneDetailsService;
import com.sixtofly.auth.server.service.impl.WebUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证服务器配置类
 * @author xie yuan bing
 * @date 2019-12-11 11:43
 * @description
 */
@Configuration
@EnableAuthorizationServer
public class SecurityAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private WebUserDetailsServiceImpl webUserDetailsService;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private PhoneDetailsService phoneDetailsService;

//    @Autowired
//    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 自定义客户端管理
        clients.withClientDetails(clientDetailsService);
//        clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenStore(tokenStore)
                // 配置令牌额外信息
                .tokenEnhancer(tokenEnhancer())
                .userDetailsService(webUserDetailsService)
                .authenticationManager(authenticationManager)
                // 异常处理
                .exceptionTranslator(new SecurityExceptionTranslator())
//                .setClientDetailsService(clientDetailsService)
                // 配置JWT令牌
//                .accessTokenConverter(jwtAccessTokenConverter)
                .reuseRefreshTokens(false)
                // 配置自定义授权模式
                .tokenGranter(
                        new CustomTokenGranter(endpoints).authenticationManager(authenticationManager)
                        .phoneDetailsService(phoneDetailsService)
                        .build()
                )
        ;
    }


    /**
     * 配置额外信息
     * @return
     */
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            final Map<String, Object> additionalInfo = new HashMap<>(4);
            SecurityUser securityUser = (SecurityUser) authentication.getUserAuthentication().getPrincipal();
            additionalInfo.put("user", securityUser.getUser());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }






}
