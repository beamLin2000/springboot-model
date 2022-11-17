package com.gxa.modules.sys.form.backstage.promotion.eventManagement;

import com.alipay.api.domain.Activity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :林溪
 * @date : 2022/11/11 10:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("活动管理->新增/编辑活动")
public class AddEvent {
    @ApiModelProperty(name = "activityTitle",value = "活动标题",dataType = "String",required = true)
    private String activityTitle;
    @ApiModelProperty(name = "listChart",value = "列表图",dataType = "String",required = true)
    private String listChart;
    @ApiModelProperty(name = "status",value = "状态",dataType = "Integer",required = true)
    private Integer status;
    @ApiModelProperty(name = "putOnShelves",value = "上架",dataType = "Integer",required = true)
    private Integer putOnShelves;
    @ApiModelProperty(name = "activityIntroduction",value = "活动介绍",dataType = "String",required = true)
    private String activityIntroduction;
    @ApiModelProperty(name = "activityTime",value = "活动时间",dataType = "String",required = true)
    private String activityTime;
}
