package com.sixtofly.auth.server.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 自定义登录逻辑-手机号码登录逻辑
 * @author xie yuan bing
 * @date 2021-01-08 15:57
 * @description
 */
public interface PhoneDetailsService {

    /**
     * 根据手机号码获取用户信息
     * @param phone 手机号码
     * @param code 验证码
     * @return 用户信息
     */
    UserDetails loadUserByPhone(String phone, String code) throws UsernameNotFoundException;;
}
