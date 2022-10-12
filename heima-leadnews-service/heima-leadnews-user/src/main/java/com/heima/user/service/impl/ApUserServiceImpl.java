package com.heima.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dtos.LoginDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.pojo.ApUser;
import com.heima.user.mapper.ApUserMapper;
import com.heima.user.service.ApUserService;
import com.heima.utils.common.AppJwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * APP用户信息表 服务实现类
 * </p>
 *
 * @author whang
 */
@Slf4j
@Service
@Transactional
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUserService {

    @Override
    public ResponseResult login(LoginDto loginDto) {
        //校验数据是否合法
        if(loginDto == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        // 判断参数中是否含有数据，如果有数据，就是正常登录；如果没有数据，就是游客登陆
        if(StringUtils.isNotEmpty(loginDto.getPhone()) && StringUtils.isNotEmpty(loginDto.getPassword())) {
            //通过手机号查询用户的信息
            ApUser apUserFromDatabase = this.getOne(Wrappers.<ApUser>lambdaQuery().eq(ApUser::getPhone, loginDto.getPhone()));
            if(apUserFromDatabase == null) {
                return ResponseResult.errorResult(AppHttpCodeEnum.AP_USER_DATA_NOT_EXIST);
            }
            //将传过来的密码加盐，再生成加密的密码
            String salt = apUserFromDatabase.getSalt();
            String passwd = loginDto.getPassword();
            passwd = DigestUtils.md5DigestAsHex((passwd + salt).getBytes());
            //对比密码，如果错误，返回密码错误提示
            if(!apUserFromDatabase.getPassword().equals(passwd)) {
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
            }

            //返回数据
            Map<String, Object> map = new HashMap<>();
            apUserFromDatabase.setPassword("");
            apUserFromDatabase.setSalt("");
            map.put("user", apUserFromDatabase);
            String token = AppJwtUtil.getToken(apUserFromDatabase.getId());
            map.put("token", token);
            return ResponseResult.okResult(map);
        }else {
            //游客身份登录，用0作为用户Id
            Map<String, Object> map = new HashMap<>();
            map.put("token", AppJwtUtil.getToken(0L));
            return ResponseResult.okResult(map);
        }
    }
}
