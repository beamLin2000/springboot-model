package com.gxa.modules.sys.controller.backStage.promotion.couponManagement;


import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.fristpage.entity.Msg;
import com.gxa.modules.fristpage.service.MsgService;
import com.gxa.modules.login.entity.User;
import com.gxa.modules.login.service.UserTokenService;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponManagementAll;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponType;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponUsageInformation;
import com.gxa.modules.sys.service.promotion.CouponManagementService;
import com.gxa.modules.sys.service.promotion.CouponUsageInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author LXD
 * @Date 2022/11/11 15:17
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/couponManger")
@Api(tags = "后台:优惠券管理")
public class CouponManagementController {

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private CouponManagementService couponManagementService;

    @Autowired
    private CouponUsageInfoService couponUsageInfoService;
    @Autowired
    private MsgService msgService;

    @GetMapping("/search")
    @ApiOperation(value = "筛选")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", value = "当前是第几页", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "limit", value = "每页显示多少条", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "couponName", value = "优惠券名称", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "couponType", value = "优惠券类型", dataType = "String")

    })
    public Result<PageUtils> search(@RequestParam @ApiIgnore Map<String,Object> params){
        log.info("查询获得的条件===={}==",params);
        PageUtils search = this.couponManagementService.search(params);
        return new Result<PageUtils>().ok(search);
    }

    @GetMapping("/list")
    @ApiOperation(value = "优惠券类型")
    public Result<CouponType> list(){
        CouponType couponType = new CouponType();
        List<String> couponTypes = new ArrayList<>();
        couponTypes.add("会员赠送");
        couponTypes.add("全场赠送");
        couponTypes.add("购物赠送");
        couponType.setCouponType(couponTypes);
        return new Result<CouponType>().ok(couponType);
    }

    @GetMapping("/searchById")
    @ApiOperation(value = "查看")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", value = "当前是第几页", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "limit", value = "每页显示多少条", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "id", value = "id", dataType = "Integer")
    })
    public Result<CouponManagementAll> searchById(@RequestParam @ApiIgnore Map<String,Object> params){
        //获取params中的id
        String id = (String) params.get("id");
        //根据id查询优惠券信息
        CouponManagementAll couponManagementAll = couponManagementService.searchById(id);
        //分页查询优惠券使用信息
        PageUtils pageUtils = couponUsageInfoService.searchCouponUsageInfo(params);
        //将查询到的数据，拿出来
        List<CouponUsageInformation> list = (List<CouponUsageInformation>) pageUtils.getList();
        //将优惠券的使用信息放入CouponManagementAll中
        couponManagementAll.setCouponUsageInformations(list);
        return new Result<CouponManagementAll>().ok(couponManagementAll);
    }

    @DeleteMapping("/deleteByIds")
    @ApiOperation(value = "删除，批量删除")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "ids", value = "删除那几条数据", dataType = "List<String>"),
    })
    public Result deleteByIds(@RequestBody @ApiIgnore List<String> ids){
        log.info("====ids{}====",ids);
        this.couponManagementService.deleteByIds(ids);
        return new Result().ok();
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存")
    public Result save(@RequestBody CouponManagementAll couponManagementAll){
        this.couponManagementService.add(couponManagementAll);
        return new Result().ok();
    }

    @GetMapping("/editPre")
    @ApiOperation(value = "编辑前的查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "查询的id", dataType = "String"),
    })
    public Result<CouponManagementAll> editPre(@RequestParam @ApiIgnore ("id") String id){
        CouponManagementAll couponManagementAll = this.couponManagementService.selectById(id);
        return new Result().ok(couponManagementAll);
    }

    @PostMapping("/edit")
    @ApiOperation(value = "编辑")
    public Result edit(@RequestBody CouponManagementAll couponManagementAll){
        log.info("====={}===",couponManagementAll);
        this.couponManagementService.edit(couponManagementAll);
        return new Result().ok();
    }


    @PostMapping("/editCoupon")
    @ApiOperation(value = "用户获取优惠券，添加优惠券")
    public Result getCoupons(HttpServletRequest request,@RequestBody CouponUsageInformation couponUsageInformation){

        this.couponUsageInfoService.addCoupons(couponUsageInformation);
        List<User> users = couponUsageInformation.getUsers();
        for (User user :
            users    ) {
            this.msgService.saveMsg(new Msg(user.getId(),null,"消息通知","你有新的优惠卷请在我的-优惠卷查看"),UUID.randomUUID().toString());
        }


        return new Result().ok();
    }

    @GetMapping("/addPre")
    @ApiOperation(value = "添加前给一个id")
    public Result addPre(){
        String s = this.couponManagementService.addPre();
        return new Result().ok(s);
    }
}
