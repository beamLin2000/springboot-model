package com.gxa.modules.goods.goodsForm;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("药品分类表单")
public class MedicinalForm {

    @ApiModelProperty("id")
    private int id;//id

    @ApiModelProperty("分类名称")
    private String categoryName;//分类名称

//    @ApiModelProperty("级别")
//    private String rank;//级别

    @ApiModelProperty("状态，显示或不显示")
    private String state;//状态

    @ApiModelProperty("备注")
    private String remarks;//备注

    @ApiModelProperty("上传人")
    private String uploader;//上传人

//    @ApiModelProperty("添加时间")
//    private Date addTime;//添加时间


}
