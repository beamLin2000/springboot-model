package com.gxa.modules.confirmOrder.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :林溪
 * @date : 2022/11/11 15:15
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel("用药人信息")
public class DrugUserInformation {
    @ApiModelProperty(name = "drugUserName",value = "用药人姓名",dataType = "String")
    private String drugUserName;
    @ApiModelProperty(name = "relationship",value = "关系",dataType = "String")
    private String relationship;
    @ApiModelProperty(name = "idCode",value = "身份证号",dataType = "String")
    private String idCode;
    @ApiModelProperty(name = "birthday",value = "出生年月",dataType = "String")
    private String birthday;
    @ApiModelProperty(name = "gender",value = "性别",dataType = "String")
    private String gender;
    @ApiModelProperty(name = "phoneNumber",value = "手机号",dataType = "String")
    private String phoneNumber;
    @ApiModelProperty(name = "historyOfDisease",value = "疾病史",dataType = "String")
    private String historyOfDisease;
    @ApiModelProperty(name = "prescription",value = "原处方",dataType = "String")
    private String prescription;
}
