package com.gxa.modules.sys.form.backstage.promotion.timeLimitedSecondKill;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :林溪
 * @date : 2022/11/11 10:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("限时秒杀->筛选")
public class Search {
    @ApiModelProperty(name = "drugName",value = "输入名称",dataType = "String")
    private String drugName;
}
