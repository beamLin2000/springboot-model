package com.gxa.modules.order.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "药品订单")
public class OrderListDto {
    @ApiModelProperty(value = "订单编号")
    private String orderNo;
    @ApiModelProperty(value = "提交时间")
    private Date commitTime;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "收货人")
    private String consignee;
    @ApiModelProperty(value = "订单金额")
    private BigDecimal orderAmount;
    @ApiModelProperty(value = "订单状态")
    private String orderStatus;
}
