package com.sixtofly.auth.server.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sixtofly.common.constants.BaseResponseCode;

/**
 * @author xie yuan bing
 * @date 2019-09-24 22:29
 * @description
 */
@JsonSerialize(using = CustomOauth2ExceptionSerializer.class)
public class ServerErrorException extends CustomOauth2Exception {

    public ServerErrorException(){
        super(BaseResponseCode.ERROR.name(), "server_error");
    }

    public ServerErrorException(String errorCode, String msg) {
        super(errorCode, msg);
    }

    public ServerErrorException(String errorCode, String msg, Throwable t) {
        super(errorCode, msg, t);
    }

    @Override
    public String getErrorCode() {

        return BaseResponseCode.ERROR.name();
    }

    @Override
    public String getMessage() {
        return "server_error";
    }
}
