package com.sixtofly.provider.base.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sixtofly.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author xie yuan bing
 * @since 2020-05-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("six_base_role_permission")
public class SixBaseRolePermission extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private Long permissionId;
}
