package com.sixtofly.auth.common.config;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.sixtofly.auth.common.entity.SecurityUser;
import com.sixtofly.auth.common.entity.UserInfo;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * JWT令牌Token转化器
 * @author xie yuan bing
 * @date 2020-01-19 14:01
 * @description
 */
public class SecurityAccessTokenConverter extends DefaultAccessTokenConverter {

    @Override
    public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        Map<String, Object> response = (Map<String, Object>) super.convertAccessToken(token, authentication);
        response.put("loginTime", DateUtil.now());
//        map.put("username", securityUser.getUsername());
//        map.put("userId", securityUser.getUserId());
//        map.put("nickName", securityUser.getNickName());
//        map.put("name", securityUser.getName());
//        map.put("authorities", securityUser.getAuthorities());
//        map.put("accountNonExpired", securityUser.isAccountNonExpired());
//        map.put("accountNonLocked", securityUser.isAccountNonLocked());
//        map.put("credentialsNonExpired", securityUser.isCredentialsNonExpired());
//        map.put("enabled", securityUser.isEnabled());
        if (authentication.getPrincipal() instanceof SecurityUser) {
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            UserInfo user = new UserInfo();
            BeanUtil.copyProperties(securityUser, user);
            response.put("user", JSONObject.toJSONString(user));
        }
        return response;
    }
}
