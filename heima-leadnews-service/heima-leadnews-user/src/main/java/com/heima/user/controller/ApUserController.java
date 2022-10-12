package com.heima.user.controller;

import com.heima.model.common.dtos.LoginDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.user.service.ApUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * APP用户信息表 前端控制器
 * </p>
 *
 * @author whang
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/login")
@Api(value = "app端用户登录-控制器")
public class ApUserController {

    @Autowired
    private ApUserService  apUserService;

    @PostMapping("/login_auth")
    @ApiOperation("用户登录")
    public ResponseResult login(@RequestBody LoginDto loginDto) {
        return apUserService.login(loginDto);

    }
}
