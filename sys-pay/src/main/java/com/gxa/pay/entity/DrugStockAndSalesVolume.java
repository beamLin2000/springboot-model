package com.gxa.pay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrugStockAndSalesVolume {
    private String id;
    private Integer dId;
    private Integer quantity;//数量
    private Integer orderNo;
    private Integer shoppState;

    private String drug_name;
    private Integer stock;
    private Integer salesVolume;
}
