package com.sixtofly.provider.base.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sixtofly.common.entity.BaseEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xie yuan bing
 * @since 2020-05-08
 */
@TableName("six_base_role")
@Data
public class SixBaseRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 备注
     */
    private String note;
}
