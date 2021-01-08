package com.sixtofly.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xie yuan bing
 * @date 2019-12-13 17:39
 * @description
 */
@Data
public class BaseVo implements Serializable {

    private static final long serialVersionUID = 8970422295171330426L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("创建时间")
    private Date gmtCreate;

    @ApiModelProperty("修改时间")
    private Date gmtModified;

    @ApiModelProperty("删除标志")
    private Boolean deleted;
}
