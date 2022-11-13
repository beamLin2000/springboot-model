package com.gxa.modules.login.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gxa.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_sys_user")
public class SysUser implements Serializable {
    @NotBlank(message = "用户名不能为空",groups = UpdateGroup.class)
    @ApiModelProperty(name = "username",value = "用户名",required = true)
    @TableField("user_name")
    private String username;

    @ApiModelProperty(name = "password",value = "旧密码",required = true)
    @TableField("password")
    private String password;


    @ApiModelProperty(name = "newPassword",value = "新密码",required = true)
    @TableField(exist = false)
    private String newPassword;

    @ApiModelProperty(name = "confirmPassword",value = "确认密码",required = true)
    @TableField(exist = false)
    private String confirmPassword;

    @TableField("salt")
    @ApiModelProperty(name = "salt",value = "盐值",hidden = true)
    private String salt;

    @NotBlank(message = "真实姓名不能为空",groups = UpdateGroup.class)
    @ApiModelProperty(name = "realName",value = "真实姓名",required = true)
    @TableField("real_name")
    private String realName;

    @NotNull(message = "手机号不能为空",groups = UpdateGroup.class)
    @ApiModelProperty(name = "phoneNumber",value = "手机号",required = true)
    @TableField("phone_number")
    private Integer phoneNumber;

    @NotBlank(message = "邮箱不能为空",groups = UpdateGroup.class)
    @ApiModelProperty(name = "email",value = "email",required = true)
    @TableField("email")
    private String email;

    @TableField("status")
    @ApiModelProperty(name = "status",value = "状态",hidden = true)
    private Integer status;
}
