package com.gxa.modules.drugUserInfo.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


//用药人信息
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("drug_user_info")

public class DrugUserInfo {
    @ApiModelProperty(name = "id",value = "id",dataType = "Integer",required = true)
    @TableField("id")
    private Integer id;

    @ApiModelProperty(name = "userId",value = "关联用户表的id",dataType = "Integer",required = true)
    @TableField("user_id")
    private Integer userId;//关联用户表

    @ApiModelProperty(name = "real_name",value = "实名状态",dataType = "String",required = true)
    @TableField("real_name")
    private String realName;//用药人姓名

    @ApiModelProperty(name = "drugUserName",value = "用药人姓名",dataType = "String",required = true)
    @TableField("drug_user_name")
    private String drugUserName;//用药人姓名

    @ApiModelProperty(name = "drugUserRelation",value = "与账号人关系",dataType = "String",required = true)
    @TableField("drug_user_relation")
    private String drugUserRelation;//与账号人关系

    @ApiModelProperty(name = "drugUserIdCard",value = "身份证号",dataType = "String",required = true)
    @TableField("drug_user_idCard")
    private String drugUserIdCard;//身份证号

    @ApiModelProperty(name = "birthday",value = "出生年月",dataType = "String",required = true)
    @TableField("birthday")
    private String birthday;//出生年月

    @ApiModelProperty(name = "gender",value = "性别",dataType = "String",required = true)
    @TableField("gender")
    private String gender;//性别

    @ApiModelProperty(name = "phone",value = "电话",dataType = "String",required = true)
    @TableField("phone")
    private String phone;//电话

    @ApiModelProperty(name = "age",value = "年龄",dataType = "Integer",required = true)
    @TableField("age")
    private Integer age;//电话

    @ApiModelProperty(name = "allergyHistory",value = "过敏史,0就是无，1就是有",dataType = "Integer",required = true)
    @TableField("allergy_history")
    private Integer allergyHistory;//过敏史,0就是无，1就是有

    @ApiModelProperty(name = "familyHistory",value = "家族病",dataType = "Integer",required = true)
    @TableField("family_history")
    private Integer familyHistory;//家族病

    @ApiModelProperty(name = "liverFunction",value = "肝功能",dataType = "Integer",required = true)
    @TableField("liver_function")
    private Integer liverFunction;//肝功能

    @ApiModelProperty(name = "renalFunction",value = "肾功能",dataType = "Integer",required = true)
    @TableField("renal_function")
    private Integer renalFunction;//肾功能

    @ApiModelProperty(name = "pregnantLactation",value = "妊娠哺乳",dataType = "Integer",required = true)
    @TableField("pregnant_lactation")
    private Integer pregnantLactation;//妊娠哺乳

    @ApiModelProperty(name = "version",value = "版本号",dataType = "Integer",required = false)
    @TableField("version")
    private Integer version;//版本号
}
