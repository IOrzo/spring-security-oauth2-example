package com.sixtofly.auth.common.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;

/**
 * Token令牌中的自定义信息
 * @author xie yuan bing
 * @date 2019-12-16 11:27
 * @description
 */
public class SecurityUser extends User implements Serializable {

    private static final long serialVersionUID = 3649248194525661111L;

    /**
     * 用户信息
     */
    private UserInfo user;


    public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public SecurityUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}
