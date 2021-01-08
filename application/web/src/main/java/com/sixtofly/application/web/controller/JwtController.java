package com.sixtofly.application.web.controller;

import com.sixtofly.auth.common.entity.SecurityUser;
import com.sixtofly.auth.common.util.JwtUserUtil;
import com.sixtofly.auth.common.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xie yuan bing
 * @date 2021-01-07 10:44
 * @description
 */
@Api(tags = "jwt令牌")
@RestController
@RequestMapping("/jwt")
public class JwtController {

    @ApiOperation("获取当前用户")
    @GetMapping("/getUser")
    public SecurityUser getUser(){
        return JwtUserUtil.getUser();
    }

}
