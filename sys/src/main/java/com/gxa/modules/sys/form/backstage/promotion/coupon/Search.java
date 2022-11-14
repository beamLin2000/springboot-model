package com.gxa.modules.sys.form.backstage.promotion.coupon;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author :林溪
 * @date : 2022/11/11 9:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("优惠券管理->筛选")
public class Search {
    @ApiModelProperty(name = "couponName",value = "输入标题",dataType = "String")
    private String couponName;
    @ApiModelProperty(name = "couponType",value = "优惠券类型",dataType = "String")
    private String couponType;
}
