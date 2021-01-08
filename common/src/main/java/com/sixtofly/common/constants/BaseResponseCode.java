package com.sixtofly.common.constants;

/**
 * 基础状态码
 * @author xie yuan bing
 * @date 2019-12-16 17:12
 * @description
 */
public enum  BaseResponseCode  {

    /**
     * 成功
     */
    SUCCESS("success"),

    /**
     * 失败
     */
    ERROR("error"),

    /**
     * TEST是状态码, msg是自定义提示信息
     * 状态码用字符串表示，用英文单词构成，比纯数字更容易理解
     */
    TEST("中文提示");

    private String msg;

    BaseResponseCode(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
