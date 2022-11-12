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
@ApiModel(value = "退款详细")
public class RefundDetailDto {
    @ApiModelProperty(value = "服务编号")
    private String applicationNo;
    @ApiModelProperty(value = "申请时间")
    private Date applicationTime;
    @ApiModelProperty(value = "申请状态")
    private String refundStatus;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "订单编号")
    private String orderNo;
    @ApiModelProperty(value = "订单金额")
    private BigDecimal orderAmount;
    @ApiModelProperty(value = "实际退款金额")
    private BigDecimal refundAmount;
    @ApiModelProperty(value = "退款方式")
    private String refundMethod;
    @ApiModelProperty(value = "退款类型")
    private String refundType;
    @ApiModelProperty(value = "退款原因")
    private String refundReason;
    @ApiModelProperty(value = "处理备注")
    private String refundMark;
    @ApiModelProperty(value = "操作人")
    private String operationPerson;
}
