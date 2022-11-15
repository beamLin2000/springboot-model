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
@ApiModel(value = "退款申请")
public class RefundListDto {
    @ApiModelProperty(value = "申请退款编号")
    private String applicationNo;
    @ApiModelProperty(value = "申请退款时间")
    private Date applicationTime;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "退款金额")
    private BigDecimal refundAmount;
    @ApiModelProperty(value = "订单编号")
    private String orderNo;
    @ApiModelProperty(value = "退款状态")
    private String refundStatus;
    @ApiModelProperty(value = "处理退款时间")
    private Date operationTime;
}
