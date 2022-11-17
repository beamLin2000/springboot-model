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
@TableName("t_user")
public class UserManagement {
    @ApiModelProperty(name = "id",value = "uuid",dataType = "String")
    @TableField("id")
    private String id;

    @ApiModelProperty(name = "userName",value = "用户姓名",dataType = "String")
    @TableField("real_name")
    private String userName;

    @ApiModelProperty(name = "headImg",value = "头像",dataType = "String")
    @TableField("head_location")
    private String headImg;

    @ApiModelProperty(name = "phoneNumber",value = "电话",dataType = "String")
    @TableField("phone_number")
    private String phoneNumber;

    @ApiModelProperty(name = "status",value = "状态",dataType = "Integer")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(name = "createTime",value = "加入时间",dataType = "String")
    @TableField("create_time")
    private String createTime;

    @TableField(exist = false)
    @ApiModelProperty(name = "tUserId",value = "当前用户id",dataType = "String")
    private String tUserId;

    @TableField("version")
    @ApiModelProperty(name ="version",value = "版本号",dataType = "Integer")
    private Integer version;
}
