package com.gxa.modules.login.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_role")
public class Role implements Serializable {
    @ApiModelProperty(name = "name",value = "角色名",required = true)
    @TableField("name")
    private String name;
    @ApiModelProperty(name = "desc",value = "描述",required = false)
    @TableField("desc")
    private String desc;

    @ApiModelProperty(name = "status",value = "状态",hidden = true)
    @TableField("status")
    private Integer status;

    @ApiModelProperty(name = "createTime",value = "创建时间",hidden = true)
    @TableField("create_time")
    private Date createTime;

//    @ApiModelProperty(name = "authority",value = "权限",required = true)
//    @TableField(exist = false)
//    private String authority;
}
