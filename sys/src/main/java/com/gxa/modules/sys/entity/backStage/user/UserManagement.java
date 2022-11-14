package com.gxa.modules.sys.entity.backStage.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author LXD
 * @Date 2022/11/11 14:53
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_userManagement")
public class UserManagement {
    @ApiModelProperty(name = "id",value = "uuid",dataType = "String")
    @TableField("id")
    private String id;

    @ApiModelProperty(name = "user_name",value = "用户姓名",dataType = "String")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(name = "headImg",value = "头像",dataType = "String")
    @TableField("head_img")
    private String headImg;

    @ApiModelProperty(name = "phoneNumber",value = "电话",dataType = "String")
    @TableField("phone_number")
    private String phoneNumber;

    @ApiModelProperty(name = "status",value = "状态",dataType = "Integer")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(name = "addTime",value = "加入时间",dataType = "String")
    @TableField("add_time")
    private String addTime;
}
