package com.gxa.modules.sys.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
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
    @Excel(name = "编号",orderNum = "0")
    private Integer id;

    @TableField("user_name")
    @Excel(name = "用户名",orderNum = "1")
    private String username;
    @Excel(name = "密码",orderNum = "2")
    private String pwd;
    @Excel(name = "盐值",orderNum = "3")
    private String salt;


}
