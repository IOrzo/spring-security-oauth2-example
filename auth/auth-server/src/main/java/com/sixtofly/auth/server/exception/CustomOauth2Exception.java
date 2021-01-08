package com.sixtofly.auth.server.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 自定义oauth2异常
 * @author xie yuan bing
 * @date 2019-09-24 22:03
 * @description
 */
@JsonSerialize(using = CustomOauth2ExceptionSerializer.class)
public class CustomOauth2Exception extends OAuth2Exception {

    private String errorCode;

    public CustomOauth2Exception(String errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public CustomOauth2Exception(String errorCode, String msg, Throwable t) {
        super(msg, t);
        this.errorCode = errorCode;
    }

    @Override
    public String getOAuth2ErrorCode() {
        return errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
