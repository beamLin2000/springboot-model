package com.gxa.modules.login.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gxa.common.utils.Result;
import com.gxa.common.validator.AssertUtils;
import com.gxa.modules.login.form.BasicInstallForm;
import com.gxa.modules.login.service.BasicInstallService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;


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
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "time",value ="取消时间",dataType ="int",required = true),
            @ApiImplicitParam(paramType = "query",name = "timeType",value ="时间格式",dataType ="String",required = true),
            @ApiImplicitParam(paramType = "query",name = "currencyFormat",value ="货币格式",dataType ="String",required = true),
            @ApiImplicitParam(paramType = "query",name = "evaluateStatus",value ="评价审核状态",dataType ="int",required = true),
            @ApiImplicitParam(paramType = "query",name = "automaticReceipt",value ="自动收货状态",dataType ="int",required = true),
            @ApiImplicitParam(paramType = "query",name = "receiptTime",value ="收货时长",dataType ="int",required = true),
            @ApiImplicitParam(paramType = "query",name = "invoiceStatus",value ="能否开发票",dataType ="int",required = true),
            @ApiImplicitParam(paramType = "query",name = "userAgreement",value ="用户协议",dataType ="String",required = true)
    }
    )
    public Result update(@RequestParam @ApiIgnore Map<String,Object> params){
        AssertUtils.isMapEmpty(params,"不能含有空");
        BasicInstallForm basicInstallForm = new BasicInstallForm();
        basicInstallForm.setTime(Integer.parseInt(params.get("time").toString()));
        basicInstallForm.setTimeType(params.get("timeType").toString());
        basicInstallForm.setCurrencyFormat(params.get("currencyFormat").toString());
        basicInstallForm.setEvaluateStatus(Integer.parseInt(params.get("evaluateStatus").toString()));
        basicInstallForm.setAutomaticReceipt(Integer.parseInt(params.get("automaticReceipt").toString()));
        basicInstallForm.setReceiptTime(Integer.parseInt(params.get("receiptTime").toString()));
        basicInstallForm.setInvoiceStatus(Integer.parseInt(params.get("invoiceStatus").toString()));
        basicInstallForm.setUserAgreement(params.get("userAgreement").toString());
        this.basicInstallService.update(basicInstallForm,new UpdateWrapper<BasicInstallForm>().eq("id",1));
        return new Result<>().ok();
    }
}
