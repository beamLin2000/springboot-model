package com.gxa.modules.confirmOrder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private Integer id;
    private Integer order_no;//订单编号
    private Double order_amount;//订单金额
    private String order_status;//订单状态

}
