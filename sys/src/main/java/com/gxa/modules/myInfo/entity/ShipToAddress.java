package com.gxa.modules.myInfo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("promotion_user_management_address")
public class ShipToAddress {
    @ApiModelProperty(hidden = true)
    @TableField("t_user_id")
    private Integer userId;

    @TableField("id")
    private String id;
    @TableField("consignee")
    private String name;
    @TableField("phone_number")
    private String phone;
    @TableField("address")
    private String address;
    @TableField("default_address")
    private String isDefault;
    @ApiModelProperty(hidden = true)
    @TableField("version")
    private Integer version;
}
