package com.gxa.pay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaitPayOrder {
    private String orderNo;
    private List<OrderTemp> orderTemps;
    private Double orderAmount;
}
