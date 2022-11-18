package com.gxa.modules.confirmOrder.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
//新建订单
public class Order {
    @ApiModelProperty(name = "id",value = "id",dataType = "Integer",required = false,hidden = true)
    private Integer id;
    @ApiModelProperty(name = "orderNo",value = "订单编号",dataType = "String",required = false,hidden = true)
    private String orderNo;//订单编号
    @ApiModelProperty(name = "orderAmount",value = "订单金额",dataType = "Double",required = true)
    private Double orderAmount;//订单金额
    @ApiModelProperty(name = "orderStatus",value = "订单状态",dataType = "Double",required = false,hidden = true)
    private String orderStatus;//订单状态
    @ApiModelProperty(name = "payMethod",value = "支付方式",dataType = "String",required = false,hidden = true)
    private String payMethod;
    @ApiModelProperty(name = "orderCreateTime",value = "创建时间",dataType = "Date",required = false,hidden = true)
    private Date orderCreateTime;//创建时间
    @ApiModelProperty(name = "userId",value = "关联用户的id",dataType = "Integer",required = false,hidden = true)
    private Integer userId;//关联用户表
    @ApiModelProperty(name = "prescriptionId",value = "关联用药人表id",dataType = "Integer",required = false,hidden = true)
    private Integer prescriptionId;//关联用药人表
    @ApiModelProperty(name = "addressId",value = "关联收货人地址id",dataType = "String",required = false,hidden = true)
    private String addressId;//关联收货人地址




}
