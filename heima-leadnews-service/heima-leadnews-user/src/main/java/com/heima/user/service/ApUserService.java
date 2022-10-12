package com.heima.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.LoginDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.pojo.ApUser;

/**
 * <p>
 * APP用户信息表 服务类
 * </p>
 *
 * @author whang
 */
public interface ApUserService extends IService<ApUser> {

    ResponseResult login(LoginDto loginDto);
}
