package com.sixtofly.auth.server.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;

import java.util.Map;

/**
 * @author xie yuan bing
 * @date 2019-12-16 11:30
 * @description
 */
public class SecurityUserAuthenticationConverter implements UserAuthenticationConverter {
    @Override
    public Map<String, ?> convertUserAuthentication(Authentication userAuthentication) {
        return null;
    }

    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        return null;
    }
}
