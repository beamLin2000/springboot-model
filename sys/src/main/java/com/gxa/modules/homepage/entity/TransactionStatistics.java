package com.gxa.modules.homepage.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionStatistics {
    @ApiModelProperty("时间")
    private Date day;
    @ApiModelProperty("销售金额")
    private Double money;
}
