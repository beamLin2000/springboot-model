package com.gxa.modules.drugUserInfo.controller;


import com.gxa.common.utils.Result;
import com.gxa.modules.drugUserInfo.entity.DrugUserInfo;
import com.gxa.modules.drugUserInfo.service.DrugUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Api(tags = "用药人")
@RestController
@Slf4j
public class DrugUserInfoController {

    @Autowired
    private DrugUserInfoService drugUserInfoService;


    /**
     *
     * @param userId
     * @return
     */
    @ApiOperation("用户下的所有用药人")
    @GetMapping("/drugUserInfo/list02")
    public Result drugUserInfoList02(@RequestParam("userId") Integer userId) {
        List<DrugUserInfo> drugUserInfos = this.drugUserInfoService.drugUserInfoList(userId);

        return new Result().ok(drugUserInfos);
    }


    /**
     *
     * @param drugUserInfo
     * @return
     */
    @ApiOperation("用药人添加接口")
    @PostMapping("/drugUserInfo/add")
    public Result drugUserInfoAdd(@RequestBody DrugUserInfo drugUserInfo){

        this.drugUserInfoService.addDrugUserInfo(drugUserInfo);
        return new Result().ok();
    }

    /**
     *
     * @param drugUserInfo
     * @return
     */
    @ApiOperation("用药人修改接口")
    @PutMapping("/drugUserInfo/update")
    public Result drugUserInfoUpdate(@RequestBody DrugUserInfo drugUserInfo){
        int i = this.drugUserInfoService.updateDrugUserInfo(drugUserInfo);
        if (i != 1){
            return  new Result().error("修改失败!!!");
        }

        return new Result().ok();
    }


}
