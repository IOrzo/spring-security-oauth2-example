package com.sixtofly.provider.base.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sixtofly.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xie yuan bing
 * @date 2019-12-12 9:14
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("six_base_user")
public class BaseUser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -8413038226928789884L;

    /**
     * 账户
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 微信-openId
     */
    private String openId;

    /**
     * 微信-用户昵称
     */
    private String nickName;

    /**
     * 微信-用户性别
     * 0: 未知, 1: 男性, 2:女性
     */
    private String gender;

    /**
     * 微信-显示 country，province，city 所用的语言
     *  en: 英文, zh_CN: 简体中文, zh_TW: 繁体中文
     */
    private String language;

    /**
     * 微信-用户所在国家
     */
    private String country;

    /**
     * 微信-用户所在省份
     */
    private String province;


    /**
     * 微信-用户所在城市
     */
    private String city;


    /**
     * 微信-用户头像
     * 用户头像图片的 URL。URL 最后一个数值代表正方形头像大小（有 0、46、64、96、132 数值可选，0 代表 640x640 的
     * 正方形头像，46 表示 46x46 的正方形头像，剩余数值以此类推。默认132），用户没有头像时该项为空。若用户更换头
     * 像，原有头像 URL 将失效。
     */
    private String avatarUrl;

    /**
     * 微信-unionId
     */
    private String unionId;

    /**
     * 账户过期状态
     */
    private Boolean accountNonExpired;

    /**
     * 账户锁定状态
     */
    private Boolean accountNonLocked;

    /**
     * 凭证过期状态
     */
    private Boolean credentialsNonExpired;

    /**
     * 账户激活状态
     */
    private Boolean enabled;
}
