package com.sixtofly.auth.server.controller;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 自定义统一登录页面
 * @author xie yuan bing
 * @date 2021-01-06 15:02
 * @description
 */
@RestController
public class LoginController {


    @GetMapping("/login.html")
    public ModelAndView login(ModelAndView mv, HttpServletRequest request) {
        String error = request.getQueryString();
        if ("error".equals(error)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                AuthenticationException ex = (AuthenticationException) session
                        .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
                String errorMsg = ex != null ? ex.getMessage() : "Invalid credentials";
                mv.addObject("errorMsg", errorMsg);
            }
        }
        mv.setViewName("login");
        return mv;
    }
}
