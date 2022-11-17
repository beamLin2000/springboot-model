package com.gxa.modules.assort.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrugDto {
    @ApiModelProperty(value = "药品名称")
    private String drugName;
    @ApiModelProperty(value = "药品图片地址")
    private String pictureUrl;
    @ApiModelProperty(value = "药品规格")
    private String specifications;
    @ApiModelProperty(value = "药品价格")
    private BigDecimal price;
}
