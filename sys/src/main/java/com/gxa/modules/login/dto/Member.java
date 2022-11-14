package com.gxa.modules.login.dto;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gxa.common.validator.group.AddGroup;
import com.gxa.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_sys_user")
public class Member implements Serializable {
    @NotBlank(message = "用户名不能为空",groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(name = "username",value = "用户名",required = true)
    @TableField("user_name")
    private String username;

    @ApiModelProperty(name = "password",value = "密码",required = true)
    @NotBlank(message = "密码不能为空",groups = {AddGroup.class, UpdateGroup.class})
    @TableField("password")
    private String password;

    @TableField("salt")
    @ApiModelProperty(name = "salt",value = "盐值",hidden = true)
    private String salt;

    @NotBlank(message = "真实姓名不能为空",groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(name = "realName",value = "真实姓名",required = true)
    @TableField("real_name")
    private String realName;

    @NotNull(message = "手机号不能为空",groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(name = "phoneNumber",value = "手机号",required = true)
    @TableField("phone_number")
    private Integer phoneNumber;

    @NotBlank(message = "邮箱不能为空",groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(name = "email",value = "email",required = true)
    @TableField("email")
    private String email;

    @NotNull(message = "状态",groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(name = "status",value = "启用状态",required = true)
    @TableField("status")
    private Integer status;

    @ApiModelProperty(name = "createTime",value = "创建时间",hidden = true)
    @TableField("create_time")
    private Date createTime;

    @NotBlank(message = "角色",groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(name = "role",value = "角色",required = true)
    @TableField(exist = false)
    private String role;
}
