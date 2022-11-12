package com.gxa.modules.sys.form.backstage.promotion.eventManagement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :林溪
 * @date : 2022/11/11 10:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("活动管理->筛选")
public class Search {
    @ApiModelProperty(name = "eventName",value = "输入标题或关键字",dataType = "String",required = true)
    private String eventName;
    @ApiModelProperty(name = "eventStatus",value = "活动状态",dataType = "String",required = true)
    private String eventStatus;
}
