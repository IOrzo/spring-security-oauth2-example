package com.sixtofly.common.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 分页结果查询
 * @author xie yuan bing
 * @date 2019-12-16 18:00
 * @description
 */
public class PageResult<T, C> {

    @ApiModelProperty("数据列表")
    private List<T> data;

    /**
     * 比如统计数据
     */
    @ApiModelProperty("自定义数据")
    private C custom;

    @ApiModelProperty("总条数")
    private long total;

    @ApiModelProperty("当前页的数量")
    private int size;

    @ApiModelProperty("总页数")
    private int pages;

    public PageResult(List<T> data, C custom, long total, int size, int pages) {
        this.data = data;
        this.custom = custom;
        this.total = total;
        this.size = size;
        this.pages = pages;
    }

    public static <T, C> ResponseResult<PageResult<T, C>> success(List<T> data, long total, int size, int pages, C custom){
        PageResult<T, C> pageResult = new PageResult<>(data, custom, total, size, pages);
        return ResponseResult.success(pageResult);
    }


    public static <T, C> ResponseResult<PageResult<T, C>> success(List<T> data, long total, int size, int pages){
        return success(data, total, size, pages, null);
    }
}
