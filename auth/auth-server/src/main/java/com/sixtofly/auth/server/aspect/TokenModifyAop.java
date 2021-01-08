package com.sixtofly.auth.server.aspect;

import com.sixtofly.common.entity.ResponseResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * 拦截 oauth/token 的返回值，保持统一返回格式
 * @author xie yuan bing
 * @date 2019-12-17 16:06
 * @description
 */
@Aspect
@Component
public class TokenModifyAop {

    @Pointcut("execution(* org.springframework.security.oauth2.provider.endpoint.TokenEndpoint.*AccessToken(..))")
    private void oauthTokenPointCut() {
    }

    /**
     * 对返回值进行拦截并修改
     */
    @Around(value = "oauthTokenPointCut()")
    private Object modifyTokenAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Object responseObject = joinPoint.proceed();
        if (responseObject != null) {
            if (responseObject instanceof ResponseEntity) {
                ResponseEntity responseEntity = (ResponseEntity) responseObject;
                if (responseEntity.getBody() instanceof DefaultOAuth2AccessToken) {
                    DefaultOAuth2AccessToken oAuth2AccessToken = (DefaultOAuth2AccessToken) responseEntity.getBody();
                    oAuth2AccessToken.setAdditionalInformation(Collections.emptyMap());
                    return getResponse(oAuth2AccessToken);
                }
            }
            return responseObject;
        }
        return null;
    }

    private ResponseEntity<ResponseResult<OAuth2AccessToken>> getResponse(OAuth2AccessToken accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        headers.set("Content-Type", "application/json;charset=UTF-8");
         return new ResponseEntity<>(ResponseResult.success(accessToken), headers, HttpStatus.OK);
    }

}
