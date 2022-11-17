package com.gxa.modules.myInfo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefundInfo {
    private String status;
    private Double price;
    private Date applicationTime;
    private Date operationTime;
    private List<OrderTemp> orderTemp;
    private String orderNo;
    private String renson;
}
