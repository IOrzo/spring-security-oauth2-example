package com.sixtofly.auth.server.config.token.granter;

import com.sixtofly.auth.server.config.provider.PhoneAuthenticationProvider;
import com.sixtofly.auth.server.service.PhoneDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义登录逻辑-自定义组合TokenGranter
 * @see org.springframework.security.oauth2.provider.CompositeTokenGranter
 * 自定义组合TokenGranter, 整合自己自定义的授权模式
 * @author xie yuan bing
 * @date 2021-01-08 16:39
 */
public class CustomTokenGranter implements TokenGranter {

    // ----- 所需必要参数 ------

    private final List<TokenGranter> tokenGranters = new ArrayList<>();
    private AuthorizationServerEndpointsConfigurer endpoints;
    private AuthenticationManager authenticationManager;
    private ClientDetailsService clientDetails;
    private OAuth2RequestFactory requestFactory;
    private AuthorizationServerTokenServices tokenServices;
    private AuthorizationCodeServices authorizationCodeServices;

    /**
     * 电话登录逻辑
     */
    private PhoneDetailsService phoneDetailsService;



    public CustomTokenGranter(AuthorizationServerEndpointsConfigurer endpoints){
        this.endpoints = endpoints;
    }

    public CustomTokenGranter build(){
        this.clientDetails = endpoints.getClientDetailsService();
        this.requestFactory = endpoints.getOAuth2RequestFactory();
        this.authorizationCodeServices = endpoints.getAuthorizationCodeServices();
        this.tokenServices = endpoints.getTokenServices();
        initTokenGranters();
        return this;
    }

    public CustomTokenGranter authenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        return this;
    }

    public CustomTokenGranter phoneDetailsService(PhoneDetailsService phoneDetailsService){
        this.phoneDetailsService = phoneDetailsService;
        return this;
    }


    @Override
    public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
        for (TokenGranter granter : tokenGranters) {
            OAuth2AccessToken grant = granter.grant(grantType, tokenRequest);
            if (grant!=null) {
                return grant;
            }
        }
        return null;
    }

    private void initTokenGranters() {
        // 添加系统原有授权模式
        tokenGranters.add(new AuthorizationCodeTokenGranter(tokenServices, authorizationCodeServices, clientDetails, requestFactory));
        tokenGranters.add(new RefreshTokenGranter(tokenServices, clientDetails, requestFactory));
        tokenGranters.add(new ImplicitTokenGranter(tokenServices, clientDetails, requestFactory));
        tokenGranters.add(new ClientCredentialsTokenGranter(tokenServices, clientDetails, requestFactory));
        // 添加自定义授权模式
        tokenGranters.add(new PhoneTokenGranter(new PhoneAuthenticationProvider(phoneDetailsService), tokenServices, clientDetails, requestFactory));
        if (authenticationManager != null) {
            tokenGranters.add(new ResourceOwnerPasswordTokenGranter(authenticationManager, tokenServices,
                    clientDetails, requestFactory));
        }
    }
}
