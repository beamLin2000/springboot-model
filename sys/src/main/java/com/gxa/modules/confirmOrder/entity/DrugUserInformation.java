package com.gxa.modules.confirmOrder.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
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
public class DrugUserInformation {
    @ApiModelProperty(name = "id",value = "id",dataType = "Integer",required = true)
    private Integer id;
    @ApiModelProperty(name = "userId",value = "userId",dataType = "Integer")
    private Integer userId;
    @ApiModelProperty(name = "drugUserName",value = "用药人姓名",dataType = "String")
    private String drugUserName;
    @ApiModelProperty(name = "drugUserRelation",value = "关系",dataType = "String")
    private String drugUserRelation;
    @ApiModelProperty(name = "drugUserIdCard",value = "身份证号",dataType = "String")
    private String drugUserIdCard;
    @ApiModelProperty(name = "age",value = "年龄",dataType = "Integer")
    private Integer age;
    @ApiModelProperty(name = "gender",value = "性别",dataType = "String")
    private String gender;
    @ApiModelProperty(name = "phone",value = "手机号",dataType = "String")
    private String phone;
    @ApiModelProperty(name = "allergyHistory",value = "过敏史",dataType = "Integer")
    private Integer allergyHistory;
    @ApiModelProperty(name = "familyHistory",value = "家族病",dataType = "Integer")
    private Integer familyHistory;
    @ApiModelProperty(name = "liverFunction",value = "肝功能",dataType = "Integer")
    private Integer liverFunction;
    @ApiModelProperty(name = "renalFunction",value = "肾功能",dataType = "Integer")
    private Integer renalFunction;
    @ApiModelProperty(name = "pregnantLactation",value = "妊娠哺乳",dataType = "Integer")
    private Integer pregnantLactation;
}
