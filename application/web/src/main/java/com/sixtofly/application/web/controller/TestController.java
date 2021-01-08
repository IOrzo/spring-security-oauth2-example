package com.sixtofly.application.web.controller;

import com.sixtofly.common.entity.ResponseResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author xie yuan bing
 * @date 2019-12-16 15:20
 * @description
 */
@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping("/valid")
    public ResponseResult valid(@NotBlank(message = "不能为空") String notBlank){
        return ResponseResult.success(notBlank);
    }
}
