package com.sixtofly.auth.server.config.provider;

import com.sixtofly.auth.server.config.token.PhoneAuthenticationToken;
import com.sixtofly.auth.server.service.PhoneDetailsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 自定义登录逻辑-自定义Provider
 * @author xie yuan bing
 * @date 2021-01-08 16:23
 * @description
 */
public class PhoneAuthenticationProvider implements AuthenticationProvider {

    /**
     * 自定义登录具体业务逻辑
     */
    private PhoneDetailsService phoneDetailsService;


    private final Log logger = LogFactory.getLog(PhoneAuthenticationProvider.class);

    private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    private UserDetailsChecker preAuthenticationChecks = new DefaultPreAuthenticationChecks();

    public PhoneAuthenticationProvider(PhoneDetailsService phoneDetailsService) {
        this.phoneDetailsService = phoneDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 自定义的Token信息
        PhoneAuthenticationToken authenticationToken = (PhoneAuthenticationToken) authentication;
        UserDetails userDetails;
        try {
            userDetails = phoneDetailsService.loadUserByPhone(String.valueOf(authenticationToken.getPrincipal()), authenticationToken.getCode());
        } catch (UsernameNotFoundException e) {
            throw new BadCredentialsException("获取用户信息出错");
        }
        if (userDetails == null){
            throw new BadCredentialsException("获取用户信息出错");
        }
        // 检查用户当前各个状态是否可用
        preAuthenticationChecks.check(userDetails);
        // 用户认证成功, 创建认证过后Token
        PhoneAuthenticationToken token = new PhoneAuthenticationToken(userDetails, userDetails.getAuthorities());
        // 设置详情信息
        token.setDetails(authenticationToken.getDetails());
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PhoneAuthenticationProvider.class.isAssignableFrom(authentication);
    }


    private class DefaultPreAuthenticationChecks implements UserDetailsChecker {
        @Override
        public void check(UserDetails user) {
            if (!user.isAccountNonLocked()) {
                logger.debug("User account is locked");

                throw new LockedException(messages.getMessage(
                        "AbstractUserDetailsAuthenticationProvider.locked",
                        "User account is locked"));
            }

            if (!user.isEnabled()) {
                logger.debug("User account is disabled");

                throw new DisabledException(messages.getMessage(
                        "AbstractUserDetailsAuthenticationProvider.disabled",
                        "User is disabled"));
            }

            if (!user.isAccountNonExpired()) {
                logger.debug("User account is expired");

                throw new AccountExpiredException(messages.getMessage(
                        "AbstractUserDetailsAuthenticationProvider.expired",
                        "User account has expired"));
            }
        }
    }
}
