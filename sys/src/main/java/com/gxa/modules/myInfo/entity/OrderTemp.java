package com.gxa.modules.myInfo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderTemp {
    private String url;
    private Integer num;
    private String name;
    private Double price;
}
