package com.sixtofly.application.web.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import com.sixtofly.provider.base.api.entity.BaseUser;
import com.sixtofly.provider.base.api.entity.dto.CreateBaseUserDto;
import com.sixtofly.provider.base.api.entity.vo.BaseUserVo;
import com.sixtofly.provider.base.api.service.IBaseUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author xie yuan bing
 * @date 2019-12-12 9:48
 * @description
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户服务")
public class BaseUserController {

    @Reference
    private IBaseUserService userService;

    @ApiOperation("添加用户")
    @PostMapping
    public Long save(@RequestBody @Validated CreateBaseUserDto dto){
        dto.setNickName(RandomUtil.randomString(6));
        return userService.save(dto);
    }


    @ApiOperation("获取用户")
    @GetMapping("/{id}")
    public BaseUserVo get(@PathVariable("id") Long id){
        BaseUser baseUser = userService.getById(id);
        BaseUserVo vo = new BaseUserVo();
        BeanUtil.copyProperties(baseUser, vo);
        return vo;
    }

}
