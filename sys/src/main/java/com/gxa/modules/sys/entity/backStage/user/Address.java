package com.gxa.modules.sys.entity.backStage.user;

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
public class Address {
    @ApiModelProperty(name ="consignee",value = "收货人",dataType = "String")
    private String consignee;
    @ApiModelProperty(name ="phoneNumber",value = "联系电话",dataType = "String")
    private String phoneNumber;
    @ApiModelProperty(name ="address",value = "详细地址",dataType = "String")
    private String address;
    @ApiModelProperty(name ="default",value = "默认收货",dataType = "Integer")
    private Integer defaultAddress;
}
