package com.gxa.modules.login.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gxa.common.validator.group.AddGroup;
import com.gxa.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user")
public class User implements Serializable {
    private Integer id;
    @TableField("user_name")
    private String username;
    @TableField("password")
    private String password;
    @TableField("salt")
    private String salt;
    @TableField("real_name")
    private String realName;
    @TableField("phone_number")
    private Integer phoneNumber;
    @TableField("email")
    private String email;
    @TableField("loc")
    private String loc;
    @TableField(exist = false)
    private Integer status;
}
