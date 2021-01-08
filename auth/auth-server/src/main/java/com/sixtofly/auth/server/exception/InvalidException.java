package com.sixtofly.auth.server.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sixtofly.common.constants.BaseResponseCode;

/**
 * @author xie yuan bing
 * @date 2019-09-24 22:30
 * @description
 */
@JsonSerialize(using = CustomOauth2ExceptionSerializer.class)
public class InvalidException extends CustomOauth2Exception {

    public InvalidException() {
        super(BaseResponseCode.ERROR.name(), "invalid_exception");
    }

    public InvalidException(String errorCode, String msg) {
        super(errorCode, msg);
    }

    public InvalidException(String errorCode, String msg, Throwable t) {
        super(errorCode, msg, t);
    }

    @Override
    public String getErrorCode() {
        return BaseResponseCode.ERROR.name();
    }

    @Override
    public String getMessage() {
        return "invalid_exception";
    }
}
