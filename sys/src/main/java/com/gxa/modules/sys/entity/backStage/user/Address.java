package com.gxa.modules.sys.entity.backStage.user;

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
@ApiModel("收货人信息")
@TableName("promotion_user_management_address")
public class Address {
    @ApiModelProperty(name = "id",value = "uuid",dataType = "String")
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

    @TableField("version")
    @ApiModelProperty(name ="version",value = "版本号",dataType = "Integer")
    private Integer version;
}
