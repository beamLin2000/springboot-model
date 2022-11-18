package com.gxa.modules.order.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "药品订单详细")
public class OrderDetailDto {
    @ApiModelProperty(value = "订单编号")
    private String orderNo;
    @ApiModelProperty(value = "订单金额")
    private BigDecimal orderAmount;
    @ApiModelProperty(value = "订单状态")
    private String orderStatus;
    @ApiModelProperty(value = "订单创建时间")
    private Date orderCancelTime;
    @ApiModelProperty(value = "订单支付方式")
    private String payMethod;
    @ApiModelProperty(value = "订单支付时间")
    private Date order_payTime;

    @ApiModelProperty(value = "订单取消时间")
    private Date cancelTime;


    @ApiModelProperty(value = "收货人姓名")
    private String realName;
    @ApiModelProperty(value = "收货人电话")
    private String phoneNumber;
    @ApiModelProperty(value = "收货人地址")
    private String loc;
    @ApiModelProperty(value = "物流公司")
    private String expressName;
    @ApiModelProperty(value = "物流单号")
    private String expressNo;
    @ApiModelProperty(value = "用药人姓名")
    private String drugUserName;
    @ApiModelProperty(value = "用药人性别")
    private String gender;
    @ApiModelProperty(value = "用药人年龄")
    private String age;
    @ApiModelProperty(value = "病历图片地址")
    private String medicalRecord;

    private List<DrugDto> drugDtos;
}
