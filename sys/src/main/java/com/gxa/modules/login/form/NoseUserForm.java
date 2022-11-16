package com.gxa.modules.login.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NoseUserForm {
    @ApiModelProperty(name = "phone",value = "手机号",required = true)
    private String phone;
    @ApiModelProperty(name = "captcha",value = "验证码",required = true)
    private String captcha;
//    private String captch;//验证码
//    private String uuid;//用于找验证码的
//    private String openId;

}
