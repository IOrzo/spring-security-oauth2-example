package com.sixtofly.common.exception;

import com.sixtofly.common.constants.BaseResponseCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 业务异常类
 * @author xie yuan bing
 * @date 2019-12-16 16:57
 * @description
 */
@Getter
@Setter
public class BusinessException extends RuntimeException implements Serializable {


    private static final long serialVersionUID = -3281647140382643694L;

    /**
     * 状态码
     * @see com.sixtofly.common.constants.BaseResponseCode
     */
    private String code;

    /**
     * 消息
     */
    private String msg;

    /**
     * 数据
     */
    private Object data;


    public BusinessException(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BusinessException(String msg) {
        this(BaseResponseCode.ERROR.name(), msg, null);
    }

    public BusinessException(BaseResponseCode responseCode, Object data) {
        this(responseCode.name(), responseCode.getMsg(), data);
    }

    public BusinessException(BaseResponseCode responseCode) {
        this(responseCode, null);
    }

}
