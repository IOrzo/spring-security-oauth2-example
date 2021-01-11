package com.sixtofly.application.web.controller;

import com.sixtofly.common.entity.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @author xie yuan bing
 * @date 2019-12-16 15:20
 * @description
 */
@Api(tags = "测试接口")
@RestController
@RequestMapping("/test")
public class TestController {

    @ApiOperation("测试单个参数校验")
    @ApiImplicitParam(name = "notBlank", value = "参数", required = true)
    @GetMapping("/valid")
    public ResponseResult valid(@NotBlank(message = "不能为空") String notBlank){
        return ResponseResult.success(notBlank);
    }
}
