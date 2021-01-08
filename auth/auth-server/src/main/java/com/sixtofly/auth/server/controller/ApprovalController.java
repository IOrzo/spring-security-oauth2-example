package com.sixtofly.auth.server.controller;

import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 自定义授权页面
 * 具体逻辑需仿造 WhitelabelApprovalEndpoint
 * @see org.springframework.security.oauth2.provider.endpoint.WhitelabelApprovalEndpoint
 * @author xie yuan bing
 * @date 2021-01-06 17:04
 * @description
 */
@Controller
@SessionAttributes("authorizationRequest")
public class ApprovalController {

    @RequestMapping("/oauth/confirm_access")
    public ModelAndView getAccessConfirmation(Map<String, Object> model, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();

        if (request.getAttribute("_csrf") != null) {
            model.put("_csrf", request.getAttribute("_csrf"));
        }
        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
        String clientId = authorizationRequest.getClientId();
        mv.addObject("clientId", clientId);
        CsrfToken csrfToken = (CsrfToken) (model.containsKey("_csrf") ? model.get("_csrf") : request.getAttribute("_csrf"));
        if (csrfToken != null) {
            mv.addObject("csrfToken", HtmlUtils.htmlEscape(csrfToken.getToken()));
        }
        mv.setViewName("approval");
        mv.addObject(model);
        return mv;
    }
}
