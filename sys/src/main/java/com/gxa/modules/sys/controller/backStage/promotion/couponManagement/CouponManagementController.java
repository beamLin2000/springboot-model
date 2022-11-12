package com.gxa.modules.sys.controller.backStage.promotion.couponManagement;


import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Result;
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
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author LXD
 * @Date 2022/11/11 15:17
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/couponManger")
@Api(tags = "优惠券管理")
public class CouponManagementController {

    @Autowired
    private CouponManagementService couponManagementService;

    @Autowired
    private CouponUsageInfoService couponUsageInfoService;

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
    public Result<CouponManagementAll> searchById(@RequestParam Map<String,Object> params){
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
            @ApiImplicitParam(paramType = "query", name = "ids", value = "删除那几条数据", dataType = "List<String>"),
    })
    public Result deleteByIds(@RequestBody List<String> ids){
        this.couponManagementService.deleteByIds(ids);
        return new Result().ok();
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "couponManagementAll", value = "保存这个实体类", dataType = "CouponManagementAll"),
    })
    public Result save(CouponManagementAll couponManagementAll){
//        List<CouponAddDrug> couponAddDrugs = new ArrayList<>();
//        CouponAddDrug couponAddDrug = new CouponAddDrug("101","drugCode","123");
//        CouponAddClass couponAddClass = new CouponAddClass("101", "classification_name", "二级");
////        CouponManagement couponManagement = new CouponManagementAll("12","13",12.0,"123",12,12,12,"123","123","123",123,"123",123,12,c);

        return new Result().ok();
    }





}
