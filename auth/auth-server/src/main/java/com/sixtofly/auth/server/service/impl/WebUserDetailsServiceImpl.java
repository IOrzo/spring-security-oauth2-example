package com.sixtofly.auth.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.sixtofly.auth.common.entity.SecurityUser;
import com.sixtofly.auth.common.entity.UserInfo;
import com.sixtofly.provider.base.api.entity.BaseUser;
import com.sixtofly.provider.base.api.service.IBaseUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * web端登录逻辑
 * @author xie yuan bing
 * @date 2019-12-11 14:49
 * @description
 */
@Service
@Slf4j
public class WebUserDetailsServiceImpl implements UserDetailsService {

    @Reference
    private IBaseUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("开始处理web端登录逻辑");
        // 获取用户信息
        BaseUser baseUser = userService.getByUsername(username);
        if (Objects.isNull(baseUser)) {
             throw new UsernameNotFoundException("用户或密码错误");
        }
        // 生成用户相关权限
        String authorityString = "queryUser,addUser";
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorityString);
        // 生成认证用户
        SecurityUser user = new SecurityUser(baseUser.getUsername(), baseUser.getPassword(), baseUser.getEnabled(), baseUser.getAccountNonExpired(), baseUser.getCredentialsNonExpired(), baseUser.getAccountNonLocked(), authorities);
        // 填充额外用户信息
        UserInfo userInfo = new UserInfo();
        BeanUtil.copyProperties(baseUser, userInfo);
        userInfo.setAuthorities(authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));
        userInfo.setRoles(Collections.EMPTY_SET);
        userInfo.setUserId(baseUser.getId());

        user.setUser(userInfo);


        log.info("web端登录逻辑处理结束");
        return user;
    }
}
