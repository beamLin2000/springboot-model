package com.gxa.modules.myInfo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
    private String name;
    private Double couponPrice;
    private String expirationDate;
}
