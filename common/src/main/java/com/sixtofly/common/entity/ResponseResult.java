package com.sixtofly.common.entity;

import com.sixtofly.common.constants.BaseResponseCode;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 基本响应结果
 * @author xie yuan bing
 * @date 2019-12-16 17:08
 * @description
 */
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = 3737209629277250398L;

    @ApiModelProperty("状态码")
    private String code;

    @ApiModelProperty("消息")
    private String msg;

    @ApiModelProperty("数据")
    private T data;

    public ResponseResult(BaseResponseCode responseCode, T data) {
        this.code = responseCode.name();
        this.msg = responseCode.getMsg();
        this.data = data;
    }

    public ResponseResult(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 响应请求结果
     * @param responseCode
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> response(BaseResponseCode responseCode, T data){
        return new ResponseResult<>(responseCode, data);
    }

    public static <T> ResponseResult<T> response(String code, String msg, T data){
        return new ResponseResult<>(code, msg, data);
    }

    /**
     * 成功响应
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> success(T data){
        return new ResponseResult<>(BaseResponseCode.SUCCESS, data);
    }

    /**
     * 成功响应
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> success(){
        return new ResponseResult<>(BaseResponseCode.SUCCESS, null);
    }

    /**
     * 失败响应
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> error(String msg, T data) {
        return new ResponseResult<>(BaseResponseCode.ERROR.name(), msg, data);
    }

    /**
     * 失败响应
     * @param msg
     * @return
     */
    public static ResponseResult error(String msg) {
        return response(BaseResponseCode.ERROR.name(), msg, null);
    }

    /**
     * 失败响应
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> error() {
        return new ResponseResult<>(BaseResponseCode.ERROR, null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
