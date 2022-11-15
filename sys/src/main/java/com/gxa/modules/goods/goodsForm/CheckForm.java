package com.gxa.modules.goods.goodsForm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("药品审核表单")
public class CheckForm {
    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("审核状态，三种，待审核，未通过，已通过")
    private String state;

    @ApiModelProperty("备注")
    private String remarks;//备注

}
