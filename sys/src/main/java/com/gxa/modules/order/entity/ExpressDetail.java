package com.gxa.modules.order.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "快递详情")
public class ExpressDetail {
    private Integer id;
    private Date expressDate;
    private String expressContent;
    private Integer expressId;
    private String orderNo;
}
