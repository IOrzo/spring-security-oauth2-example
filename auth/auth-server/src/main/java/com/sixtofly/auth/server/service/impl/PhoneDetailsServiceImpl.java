package com.sixtofly.auth.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.sixtofly.auth.common.entity.SecurityUser;
import com.sixtofly.auth.common.entity.UserInfo;
import com.sixtofly.auth.server.service.PhoneDetailsService;
import com.sixtofly.common.exception.BusinessException;
import com.sixtofly.provider.base.api.entity.BaseUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 自定义登录逻辑-手机号具体登录逻辑
 * @author xie yuan bing
 * @date 2021-01-08 16:45
 */
@Service
@Slf4j
public class PhoneDetailsServiceImpl implements PhoneDetailsService {

    @Override
    public UserDetails loadUserByPhone(String phone, String code) throws UsernameNotFoundException {
        // 模拟登录过程
        if (StringUtils.isBlank(phone)) {
            return null;
        }
        // 判断验证码
        if (!"code".equals(code)) {
            throw new BusinessException("验证码错误");
        }

        // 创建假用户
        BaseUser baseUser = new BaseUser();
        baseUser.setNickName("virtual");
        baseUser.setId(new Random(System.currentTimeMillis()).nextLong());

        // 生成用户相关权限
        String authorityString = "queryUser,addUser";
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorityString);
        // 生成认证用户
        SecurityUser user = new SecurityUser(phone, "password", authorities);

        // 填充额外用户信息
        UserInfo userInfo = new UserInfo();
        BeanUtil.copyProperties(baseUser, userInfo);
        userInfo.setAuthorities(authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));
        userInfo.setRoles(Collections.EMPTY_SET);
        userInfo.setUserId(baseUser.getId());

        user.setUser(userInfo);

        return user;
    }
}
