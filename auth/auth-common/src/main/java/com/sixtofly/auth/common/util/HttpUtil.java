package com.sixtofly.auth.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sixtofly.common.entity.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xie yuan bing
 * @date 2020-01-17 17:19
 * @description
 */
public class HttpUtil {

    /**
     * 输出json
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 返回标准的错误信息格式
     * @param response
     * @param msg
     * @param obj
     * @throws IOException
     */
    public static void error(HttpServletRequest request, HttpServletResponse response, String msg, Object obj) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(ResponseResult.error(msg, obj)));
    }

    /**
     * 返回标准信息格式
     * @param response
     * @param msg
     * @param obj
     * @throws IOException
     */
    public static void print(HttpServletRequest request, HttpServletResponse response, String resCode, String msg, Object obj) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(ResponseResult.response(resCode, msg, obj)));
    }
}
