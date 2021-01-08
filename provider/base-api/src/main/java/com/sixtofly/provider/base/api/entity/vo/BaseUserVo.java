package com.sixtofly.provider.base.api.entity.vo;

import com.sixtofly.common.entity.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xie yuan bing
 * @date 2019-12-13 17:37
 * @description
 */
@Data
public class BaseUserVo extends BaseVo implements Serializable {

    @ApiModelProperty(value = "账户")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty("账户过期状态")
    private Boolean accountNonExpired;

    @ApiModelProperty("账户锁定状态")
    private Boolean accountNonLocked;

    @ApiModelProperty("凭证过期状态")
    private Boolean credentialsNonExpired;

    @ApiModelProperty("账户激活状态")
    private Boolean enabled;
}
