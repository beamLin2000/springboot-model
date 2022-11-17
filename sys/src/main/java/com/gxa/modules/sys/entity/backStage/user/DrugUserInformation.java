package com.gxa.modules.sys.entity.backStage.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@ApiModel("用药人信息")
@TableName("drug_user_info")
public class DrugUserInformation {
    @TableId("id")
    @ApiModelProperty(name = "id",value = "id",dataType = "Integer")
    private Integer id;

    @TableField("user_id")
    @ApiModelProperty(name = "userId",value = "userId",dataType = "Integer")
    private Integer userId;

    @TableField("drug_user_name")
    @ApiModelProperty(name = "drugUserName",value = "用药人姓名",dataType = "String")
    private String drugUserName;

    @TableField("drug_user_relation")
    @ApiModelProperty(name = "drugUserRelation",value = "关系",dataType = "String")
    private String drugUserRelation;

    @TableField("drug_user_idCard")
    @ApiModelProperty(name = "drugUserIdCard",value = "身份证号",dataType = "String")
    private String drugUserIdCard;
//    @ApiModelProperty(name = "age",value = "年龄",dataType = "Integer")
//    private Integer age;

    @TableField("gender")
    @ApiModelProperty(name = "gender",value = "性别",dataType = "String")
    private String gender;

    @TableField("birthday")
    @ApiModelProperty(name = "birthday",value = "出生日期",dataType = "String")
    private String birthday;

    @TableField("phone")
    @ApiModelProperty(name = "phone",value = "手机号",dataType = "String")
    private String phone;

//    @ApiModelProperty(name = "allergyHistory",value = "过敏史",dataType = "Integer")
//    @TableField("allergy_history")
//    private Integer allergyHistory;

    @TableField("family_history")
    @ApiModelProperty(name = "familyHistory",value = "家族病/疾病史",dataType = "Integer")
    private Integer familyHistory;
//    @ApiModelProperty(name = "liverFunction",value = "肝功能",dataType = "Integer")
//    private Integer liverFunction;
//    @ApiModelProperty(name = "renalFunction",value = "肾功能",dataType = "Integer")
//    private Integer renalFunction;
//    @ApiModelProperty(name = "pregnantLactation",value = "妊娠哺乳",dataType = "Integer")
//    private Integer pregnantLactation;
    @ApiModelProperty(name = "prescription",value = "原处方",dataType = "String")
    @TableField("medical_record")
    private String prescription;

    @TableField("version")
    @ApiModelProperty(name ="version",value = "版本号",dataType = "Integer")
    private Integer version;
}
