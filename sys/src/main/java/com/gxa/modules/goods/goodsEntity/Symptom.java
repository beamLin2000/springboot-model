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
 * 症状分类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("症状分类表单")
@TableName("symptom")
public class Symptom {

    @TableField("`id`")
    @ApiModelProperty("id")
    private String id;

    @TableField("`symptom_name`")
    @ApiModelProperty("症状名称")
    private String symptomName;//症状名称

    @TableField("`rank`")
    @ApiModelProperty("级别")
    private String rank;//级别

    @TableField("`state`")
    @ApiModelProperty("状态，显示或者不显示")
    private String state;//状态

    @TableField("`remarks`")
    @ApiModelProperty("备注")
    private String remarks;//备注

    @TableField("`upload`")
    @ApiModelProperty("上传人")
    private String upload;//上传人

    @TableField("`add_time`")
    @ApiModelProperty("添加时间")
    private Date addTime;//添加时间

    @TableField("`version`")
    @ApiModelProperty("版本号")
    private int version;//版本号

    @TableField("`higher_level`")
    @ApiModelProperty("上级编号")
    private String higherLevel;//上级编号
}
