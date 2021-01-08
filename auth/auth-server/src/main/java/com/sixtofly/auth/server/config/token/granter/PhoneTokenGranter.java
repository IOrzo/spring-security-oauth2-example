package com.sixtofly.auth.server.config.token.granter;

import com.sixtofly.auth.server.config.token.PhoneAuthenticationToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 自定义登录逻辑-自定义Granter
 * @author xie yuan bing
 * @date 2021-01-08 16:10
 * @description
 */
public class PhoneTokenGranter extends AbstractTokenGranter {

    private static final String GRANT_TYPE = "phone";

    private final AuthenticationProvider authenticationProvider;

    protected PhoneTokenGranter(AuthenticationProvider authenticationProvider, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        this(authenticationProvider, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
    }

    protected PhoneTokenGranter(AuthenticationProvider authenticationProvider, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());
        String phone = parameters.get("phone");
        parameters.remove("phone");
        String code = parameters.get("code");
        parameters.remove("code");

        Authentication userAuth = new PhoneAuthenticationToken(phone, code);
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);

        // 委托给authenticationProvider执行, 找到相应的Provider处理
        try {
            userAuth = authenticationProvider.authenticate(userAuth);
        }
        catch (AccountStatusException ase) {
            //covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
            throw new InvalidGrantException(ase.getMessage());
        }
        catch (BadCredentialsException e) {
            throw new InvalidGrantException(e.getMessage());
        }
        if (userAuth == null || !userAuth.isAuthenticated()) {
            throw new InvalidGrantException("Could not authenticate user: " + code);
        }

        // 创建OAuth2Request请求
        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, userAuth);
    }
}
