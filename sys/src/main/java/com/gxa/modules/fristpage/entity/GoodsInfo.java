package com.gxa.modules.fristpage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsInfo {
    private String imageUrl;
    private Double price;
    private Integer num;
    private String name;
    private String rx;
    private String spec;//规格
    private String mainFunction;
    private String usage;
    private String approvalNum;
    private String instructionsUrl;
    private String detailsUrl;
}
