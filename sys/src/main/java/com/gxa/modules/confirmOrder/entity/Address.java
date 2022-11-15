package com.gxa.modules.confirmOrder.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :林溪
 * @date : 2022/11/11 15:09
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("promotion_user_management_address")
public class Address {
    @ApiModelProperty(name = "id",value = "uuid",dataType = "String",required = true)
    @TableField("id")
    private String id;

    @TableField("consignee")
    @ApiModelProperty(name ="consignee",value = "收货人",dataType = "String")
    private String consignee;

    @TableField("phone_number")
    @ApiModelProperty(name ="phoneNumber",value = "联系电话",dataType = "String")
    private String phoneNumber;

    @TableField("address")
    @ApiModelProperty(name ="address",value = "详细地址",dataType = "String")
    private String address;

    @TableField("default_address")
    @ApiModelProperty(name ="defaultAddress",value = "默认收货",dataType = "Integer")
    private Integer defaultAddress;

    @TableField("t_user_id")
    @ApiModelProperty(name ="userId",value = "关联用户id",dataType = "String")
    private String userId;

    @TableField("version")
    @ApiModelProperty(name ="version",value = "幂等版本号",dataType = "Integer")
    private Integer version;
}
