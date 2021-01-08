package com.sixtofly.auth.common.config;

import com.sixtofly.auth.common.util.HttpUtil;
import com.sixtofly.common.constants.BaseResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理
 * @author xie yuan bing
 * @date 2020-01-17 17:19
 * @description
 */
@Slf4j
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        log.error("", exception);
        String msg;
        Throwable cause = exception.getCause();
        if (cause instanceof InvalidTokenException) {
            response.setHeader("Authentication-Error", "1");
            msg = "登录已过期";
        } else {
            msg = "验证失败";
        }
        HttpUtil.print(request, response, BaseResponseCode.ERROR.name(), msg, exception.getMessage());
    }
}
