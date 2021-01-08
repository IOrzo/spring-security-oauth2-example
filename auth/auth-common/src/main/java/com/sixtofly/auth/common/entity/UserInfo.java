package com.sixtofly.auth.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 登录后用户常用信息
 * @author xie yuan bing
 * @date 2020-01-19 16:14
 * @description
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -7806831424962872444L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 账户
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 微信昵称
     */
    private String nickName;

    /**
     * 角色
     */
    @JsonIgnore
    private Set<String> roles;

    /**
     * 权限
     */
    @JsonIgnore
    private Set<String> authorities;

}
