package com.gxa.modules.drugUserInfo.controller;


import com.gxa.common.utils.Result;
import com.gxa.modules.drugUserInfo.entity.DrugUserInfo;
import com.gxa.modules.drugUserInfo.entity.DrugUserInfoForm;
import com.gxa.modules.drugUserInfo.service.DrugUserInfoService;
import com.gxa.modules.login.entity.User;
import com.gxa.modules.login.service.UserTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;



@Api(tags = "用药人（前台）")
@RestController
@Slf4j
public class DrugUserInfoController {

    @Autowired
    private DrugUserInfoService drugUserInfoService;

    @Autowired
    private UserTokenService userTokenService;


    @ApiOperation("添加过后的用药人")
    @GetMapping("/drugUserInfo/list0001")
    public Result drugUserInfoList0001(HttpServletRequest request) {
        String token = request.getHeader("token");
        User user = this.userTokenService.validateUserToken(token);
        System.out.println("user==============="+user);
        List<DrugUserInfoForm> drugUserInfoForms = this.drugUserInfoService.selectAll(user.getId());



        return new Result().ok(drugUserInfoForms);
    }




    /**
     *
     * @param
     * @return
     */
    @ApiOperation("用户下的所有用药人")
    @GetMapping("/drugUserInfo/list02")
    public Result drugUserInfoList02(HttpServletRequest request) {
        String token = request.getHeader("token");
        User user = this.userTokenService.validateUserToken(token);
        System.out.println("user==============="+user);
        List<DrugUserInfo> drugUserInfos = this.drugUserInfoService.selectDrugUserInfoP(user.getId());




        return new Result().ok(drugUserInfos);
    }


    /**
     *
     * @param drugUserInfo
     * @return
     */
    @ApiOperation("用药人添加接口")
    @PostMapping("/drugUserInfo/add")
    public Result drugUserInfoAdd(HttpServletRequest request,@RequestBody DrugUserInfoForm drugUserInfo){
//        drugUserInfo.setUserId(6);
        drugUserInfo.setVersion(1);
        String token = request.getHeader("token");
        User user = this.userTokenService.validateUserToken(token);
        drugUserInfo.setUserId(user.getId());
        int i = this.drugUserInfoService.addDrugUserInfoForm(drugUserInfo);
        if (i != 1){
            return new Result().error("添加失败！！！");
        }
        return new Result().ok();
    }

    /**
     *
     * @return
     */
    @ApiOperation("添加前获得id")
    @GetMapping("/drugUserInfo/drugUserInfogetid")
    public Result getId(){
        int id = this.drugUserInfoService.id();
        return new Result().ok(id);
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
