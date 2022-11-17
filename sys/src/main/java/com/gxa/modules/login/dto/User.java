package com.gxa.modules.login.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.gxa.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class User {
    
    private String username;
    
    private String realName;
    
    private String phoneNumber;
    
    private String email;
}
