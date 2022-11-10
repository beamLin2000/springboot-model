package com.gxa.modules.login.form;

import com.baomidou.mybatisplus.annotation.TableField;
import com.gxa.common.validator.group.AddGroup;
import com.gxa.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserForm {
    @TableField("user_name")
    @ApiModelProperty(name = "username",value = "用户名",required = true)
    private String username;
    @ApiModelProperty(name = "password",value = "密码",required = true)
    @TableField("password")
    private String password;
//    private String captch;//验证码
//    private String uuid;//用于找验证码的

}
