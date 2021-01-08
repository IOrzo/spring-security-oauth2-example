package com.sixtofly.application.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试权限相关接口
 * @author xie yuan bing
 * @date 2021-01-07 09:54
 * @description
 * TODO JWT令牌权限需自己实现
 */
@RestController
@RequestMapping("/authorization")
@Api(tags = "授权模块")
public class AuthorizationController {


    /**
     * TODO #oauth2 表达式需配置 OAuth2WebSecurityExpressionHandler, 配置无效, 待研究
     * @return
     */
    @ApiOperation("测试单个权限范围")
    @GetMapping("/hasScope")
    @PreAuthorize("#oauth2.hasScope('read')")
    public String hasScope() {
        return "success";
    }

    @ApiOperation("测试多个权限范围")
    @GetMapping("/hasAnyScope")
    @PreAuthorize("#oauth2.hasAnyScope('read', 'write')")
    public String hasAnyScope() {
        return "success";
    }

    @ApiOperation("测试单个权限")
    @GetMapping("/hasAuthority")
    @PreAuthorize("hasAuthority('admin')")
    public String hasAuthority() {
        return "success";
    }

    @ApiOperation("测试多个权限")
    @GetMapping("/hasAnyAuthority")
    @PreAuthorize("hasAnyAuthority('admin', 'queryUser')")
    public String hasAnyAuthority() {
        return "success";
    }

    @ApiOperation("测试单个角色")
    @GetMapping("/hasRole")
    @PreAuthorize("hasRole('admin')")
    public String hasRole() {
        return "success";
    }
}
