package com.heima.model.user.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * APP用户信息表
 * </p>
 *
 * @author whang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ap_user")
@ApiModel(value="ApUser", description="APP用户信息表")
public class ApUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "密码、通信等加密盐")
    @TableField("salt")
    private String salt;

    @ApiModelProperty(value = "用户名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "密码,md5加密")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "头像")
    @TableField("image")
    private String image;

    @ApiModelProperty(value = "0 男    1 女    2 未知")
    @TableField("sex")
    private Short sex;

    @ApiModelProperty(value = "0 未    1 是")
    @TableField("is_certification")
    private Boolean isCertification;

    @ApiModelProperty(value = "是否身份认证")
    @TableField("is_identity_authentication")
    private Boolean isIdentityAuthentication;

    @ApiModelProperty(value = "0正常    1锁定")
    @TableField("status")
    private Boolean status;

    @ApiModelProperty(value = "0 普通用户    1 自媒体人    2 大V")
    @TableField("flag")
    private Short flag;

    @ApiModelProperty(value = "注册时间")
    @TableField("created_time")
    private Date createdTime;

}
