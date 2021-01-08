package com.sixtofly.auth.server.config.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 自定义登录逻辑-自定义Token
 * @author xie yuan bing
 * @date 2021-01-08 16:03
 * @description
 */
public class PhoneAuthenticationToken extends AbstractAuthenticationToken {

    /**
     * 手机号码
     */
    private final Object principal;

    /**
     * 验证码
     */
    private String code;

    /**
     * 用户还未认证的构造函数
     * @param principal
     * @param code
     */
    public PhoneAuthenticationToken(Object principal, String code) {
        super(null);
        this.principal = principal;
        this.code = code;
        super.setAuthenticated(false);
    }

    /**
     * 用户已认证的构造函数
     * @param principal
     * @param authorities
     */
    public PhoneAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }

    public String getCode() {
        return code;
    }
}
