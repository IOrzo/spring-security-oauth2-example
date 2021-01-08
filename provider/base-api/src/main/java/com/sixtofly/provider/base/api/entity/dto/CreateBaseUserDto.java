package com.sixtofly.provider.base.api.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author xie yuan bing
 * @date 2019-12-13 17:20
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBaseUserDto implements Serializable {

    @ApiModelProperty(value = "账户", required = true)
    @NotBlank
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank
    private String password;

    /**
     * 微信-用户昵称
     */
    private String nickName;

    @ApiModelProperty("账户过期状态")
    private Boolean accountNonExpired;

    @ApiModelProperty("账户锁定状态")
    private Boolean accountNonLocked;

    @ApiModelProperty("凭证过期状态")
    private Boolean credentialsNonExpired;

    @ApiModelProperty("账户激活状态")
    private Boolean enabled;
}
