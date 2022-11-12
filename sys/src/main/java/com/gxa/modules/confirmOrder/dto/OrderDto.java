package com.gxa.modules.confirmOrder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Integer id;
    private String address;//地址
    private String drug;//药品
    private String packing;//包装

}
