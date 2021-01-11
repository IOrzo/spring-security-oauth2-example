package com.sixtofly.application.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.sixtofly.auth.common.entity.UserInfo;
import com.sixtofly.auth.common.util.SecurityUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Set;

/**
 * @author xie yuan bing
 * @date 2019-12-16 15:52
 * @description
 */
@RestController
@RequestMapping("/security/user")
@Api(tags = "认证模块")
public class SecurityController {

    @ApiOperation("获取authentication")
    @GetMapping("/getAuthentication")
    public Authentication getAuthentication(){
        return SecurityUtil.getAuthentication();
    }

    @ApiOperation("获取用户")
    @GetMapping("/getUser")
    public UserInfo getUser(){
        return SecurityUtil.tryGetUser();
    }

    @ApiOperation("获取用户角色")
    @GetMapping("/getRoles")
    public Set<String> getRoles(){
        return SecurityUtil.getRoles();
    }

    @ApiOperation("JWT解析令牌信息")
    @GetMapping("/jwt")
    public UserInfo getUserByJwt(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        token = token.replaceFirst("^[b|B]earer ", "");
        String key = Base64.getEncoder().encodeToString("signingKey".getBytes());
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        UserInfo user = JSONObject.parseObject(claims.get("user", String.class), UserInfo.class);
        return user;
    }
}


