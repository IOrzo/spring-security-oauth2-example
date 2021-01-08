package com.sixtofly.auth.server.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sixtofly.common.entity.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xie yuan bing
 * @date 2020-01-17 17:19
 * @description
 */
@Component
public class SecurityAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            response.setStatus(HttpStatus.OK.value());
            response.setHeader("Content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);
            String result = objectMapper.writeValueAsString(ResponseResult.error(exception.getMessage()));
            response.getWriter().write(result);
    }
}
