package com.gxa.modules.login.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user")
public class User implements Serializable {
    @TableField(exist = false)
    private Integer id;
    @TableField("real_name")
    private String realName;
    @TableField("phone_number")
    private String phoneNumber;
    @TableField("email")
    private String email;
    @TableField("loc")
    private String loc;
    @TableField(exist = false)
    private Integer status;
    @TableField("head_portrait")
    private String headPortrait;
    @TableField("open_id")
    private String openId;
    @TableField("user_name")
    private String username;
    @TableField("city")
    private String city;
    @TableField("province")
    private String province;
    @TableField("gender")
    private Integer gender;


}
