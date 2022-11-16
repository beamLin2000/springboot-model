package com.gxa.modules.login.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;



@Data
public class WeiChat {
    @ApiModelProperty(name = "code",value = "code",required = false)
    private String code;
//    @ApiModelProperty(name = "rawData",value = "用户非敏感信息",required = false)
//    private String rawData;
//    @ApiModelProperty(name = "signature",value = "签名",required = false)
//    private String signature;
    @ApiModelProperty(name = "sessionId",value = "sessionId",required = false)
    private String sessionId;
    @ApiModelProperty(name = "data",value = "加密数据",required = false)
    private String data;
    @ApiModelProperty(name = "iv",value = "iv",required = false)
    private String iv;

}
