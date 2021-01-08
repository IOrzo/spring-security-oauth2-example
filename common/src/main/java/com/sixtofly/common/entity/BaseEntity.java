package com.sixtofly.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xie yuan bing
 * @date 2019-12-12 9:26
 * @description
 */
@Data
public class BaseEntity implements Serializable {


    private static final long serialVersionUID = 6305795297277333359L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 删除标志
     */
    private Boolean deleted;
}
