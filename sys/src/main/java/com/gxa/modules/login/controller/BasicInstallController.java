package com.gxa.modules.login.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gxa.common.utils.Result;
import com.gxa.common.validator.ValidatorUtils;
import com.gxa.common.validator.group.UpdateGroup;
import com.gxa.modules.login.form.BasicInstallForm;
import com.gxa.modules.login.service.BasicInstallService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(tags = "基本设置")
@RequestMapping("/basic")
public class BasicInstallController {

    @Autowired
    private BasicInstallService basicInstallService;

    @ApiOperation("基本设置查询")
    @GetMapping("/query")
    public Result<BasicInstallForm> queryUpdate(){
        BasicInstallForm basicInstallForm = this.basicInstallService.queryAll();
        return new Result<BasicInstallForm>().ok(basicInstallForm);
    }

    @ApiOperation("基本设置修改")
    @PutMapping("/update")
    public Result update(@RequestBody BasicInstallForm basicInstallForm){

        ValidatorUtils.validateEntity(basicInstallForm, UpdateGroup.class);
        this.basicInstallService.update(basicInstallForm,new UpdateWrapper<BasicInstallForm>().eq("id",1));
        return new Result<>().ok();
    }
}
