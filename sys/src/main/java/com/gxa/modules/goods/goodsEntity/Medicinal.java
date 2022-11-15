package com.gxa.modules.goods.goodsEntity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 药品分类管理
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("药品分类表单")
@TableName("medicinal")
public class Medicinal {

    @ApiModelProperty("id")
    private String id;//id

    @ApiModelProperty("分类名称")
    @TableField("`category_name`")
    private String categoryName;//分类名称

    @ApiModelProperty("级别")
    @TableField("`rank`")
    private String rank;//级别

    @ApiModelProperty("状态，显示或者不显示")
    @TableField("`state`")
    private String state;//状态

    @ApiModelProperty("备注")
    private String remarks;//备注

    @ApiModelProperty("上传人")
    private String uploader;//上传人

    @ApiModelProperty("添加时间")
    @TableField("add_time")
    private Date addTime;//添加时间

    @ApiModelProperty("版本号")
    @TableField("`version`")
    private int version;//版本号

    @TableField("`higher_level`")
    @ApiModelProperty("上级编号")
    private String higherLevel;//上级编号


}
