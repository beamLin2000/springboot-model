package com.gxa.modules.order.dto;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "快递公司接口")
@TableName("express")
public class ExpressDto {
    private int id;
    @ApiModelProperty(value = "快递公司名称")
    private String expressName;
}
