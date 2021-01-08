package com.sixtofly.provider.base.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sixtofly.provider.base.api.entity.BaseUser;
import com.sixtofly.provider.base.api.entity.dto.CreateBaseUserDto;

/**
 * @author xie yuan bing
 * @date 2019-12-12 10:40
 * @description
 */
public interface IBaseUserService extends IService<BaseUser> {

    BaseUser getByUsername(String username);

    Long save(CreateBaseUserDto dto);
}
