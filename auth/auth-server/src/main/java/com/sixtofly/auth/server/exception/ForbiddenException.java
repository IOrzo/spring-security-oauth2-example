package com.sixtofly.auth.server.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sixtofly.common.constants.BaseResponseCode;

/**
 * @author xie yuan bing
 * @date 2019-09-24 22:31
 * @description
 */
@JsonSerialize(using = CustomOauth2ExceptionSerializer.class)
public class ForbiddenException extends CustomOauth2Exception {

    public ForbiddenException() {
        super(BaseResponseCode.ERROR.name(), "access_denied");
    }

    public ForbiddenException(String errorCode, String msg) {
        super(errorCode, msg);
    }

    public ForbiddenException(String errorCode, String msg, Throwable t) {
        super(errorCode, msg, t);
    }

    @Override
    public String getErrorCode() {
        return BaseResponseCode.ERROR.name();
    }

    @Override
    public String getMessage() {
        return "access_denied";
    }
}
