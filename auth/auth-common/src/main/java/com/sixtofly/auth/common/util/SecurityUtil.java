package com.sixtofly.auth.common.util;

import com.sixtofly.auth.common.entity.SecurityUser;
import com.sixtofly.auth.common.entity.UserInfo;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 普通型令牌获取用户信息
 * @author xie yuan bing
 * @date 2019-12-16 15:57
 * @description
 */
@UtilityClass
@Slf4j
public class SecurityUtil {

    /**
     * 角色前缀
     */
    private static final String ROLE_PREFIX = "ROLE_";

    /**
     * 获取Authentication
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     */
    public UserInfo getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserInfo) {
            return (UserInfo) principal;
        }
        return null;
    }

    /**
     * 尝试获取用户，若用户不存在返回Null
     * @return
     */
    public UserInfo tryGetUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return getUser(authentication);
    }

    public UserInfo getUser() throws Exception {
        UserInfo user = tryGetUser();
        if (user == null) {
            throw new Exception();
        }
        return user;
    }

    /**
     * 获取用户角色信息
     *
     * @return 角色集合
     */
    public Set<String> getRoles() {
        Authentication authentication = getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
    }
}
