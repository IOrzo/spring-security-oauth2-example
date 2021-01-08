package com.sixtofly.provider.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sixtofly.provider.base.api.entity.BaseUser;
import com.sixtofly.provider.base.api.entity.dto.CreateBaseUserDto;
import com.sixtofly.provider.base.api.service.IBaseUserService;
import com.sixtofly.provider.base.mapper.BaseUserMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * 用户服务
 * @author xie yuan bing
 * @date 2019-12-12 10:54
 * @description
 */
@Service
public class BaseUserServiceImpl extends ServiceImpl<BaseUserMapper, BaseUser> implements IBaseUserService {

    @Resource
    private BaseUserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public BaseUser getByUsername(String username) {
        QueryWrapper<BaseUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(BaseUser::getUsername, username)
                .eq(BaseUser::getDeleted, false);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public Long save(CreateBaseUserDto dto) {
        BaseUser user = new BaseUser();
        BeanUtil.copyProperties(dto, user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.save(user);
        return user.getId();
    }


}
