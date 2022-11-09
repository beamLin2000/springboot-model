package com.gxa.pay.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order {
    private String id;
    private String name;
    private BigDecimal price;
    private String desc;
}
