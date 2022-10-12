package com.heima.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.user.pojo.ApUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * APP用户信息表 Mapper 接口
 * </p>
 *
 * @author whang
 */
@Mapper
public interface ApUserMapper extends BaseMapper<ApUser> {

}
