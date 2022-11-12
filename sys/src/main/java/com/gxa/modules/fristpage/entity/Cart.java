package com.gxa.modules.fristpage.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @ApiModelProperty(value = "id",hidden = true)
    private Integer id;
    @ApiModelProperty(value = "drugId")
    private Integer drugId;
    @ApiModelProperty(value = "userId")
    private Integer userId;
    @ApiModelProperty(value = "num")
    private Integer num;
}
