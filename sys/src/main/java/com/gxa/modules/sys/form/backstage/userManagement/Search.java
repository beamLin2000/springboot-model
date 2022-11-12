package com.gxa.modules.sys.form.backstage.userManagement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :林溪
 * @date : 2022/11/11 14:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户管理->筛选")
public class Search {
    @ApiModelProperty(value = "输入用户",name = "username",dataType = "String")
    private String username;
    @ApiModelProperty(value = "注册时间",name = "signUpTime",dataType = "String")
    private String signUpTime;
}
