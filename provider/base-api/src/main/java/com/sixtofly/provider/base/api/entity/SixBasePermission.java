package com.sixtofly.provider.base.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.sixtofly.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("six_base_permission")
public class SixBasePermission extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private String code;

    private String url;

    private Boolean menu;

    private Long parentId;

    private Integer sort;

    private String note;
}
