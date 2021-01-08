package com.sixtofly.auth.server.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sixtofly.common.constants.BaseResponseCode;

/**
 * @author xie yuan bing
 * @date 2019-09-24 22:30
 * @description
 */
@JsonSerialize(using = CustomOauth2ExceptionSerializer.class)
public class MethodNotAllowed extends CustomOauth2Exception {

    public MethodNotAllowed() {
        super(BaseResponseCode.ERROR.name(), "method_not_allowed");
    }

    public MethodNotAllowed(String errorCode, String msg) {
        super(errorCode, msg);
    }

    public MethodNotAllowed(String errorCode, String msg, Throwable t) {
        super(errorCode, msg, t);
    }

    @Override
    public String getErrorCode() {
        return BaseResponseCode.ERROR.name();
    }

    @Override
    public String getMessage() {
        return "method_not_allowed";
    }
}
