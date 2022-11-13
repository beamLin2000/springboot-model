package com.gxa.modules.order.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gxa.common.utils.Result;
import com.gxa.modules.order.dto.*;

import com.gxa.modules.order.service.OrderService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


import java.util.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @ApiOperation(value="订单分页查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前是第几页",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "limit",value ="每页显示多少条",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "orderStatus",value ="查询条件订单状态",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "orderNo",value ="查询条件订单编号",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "startTime",value ="查询条件开始时间(时间戳)",dataType ="date"),
            @ApiImplicitParam(paramType = "query",name = "endTime",value ="查询条件结束时间(时间戳)",dataType ="date"),
    }
    )
    @PostMapping("/drugOrderList")
    public Result drugOrderList(@RequestParam @ApiIgnore Map<String,Object> params){
        Integer page = Integer.parseInt(params.get("page").toString());
        Integer limit = Integer.parseInt(params.get("limit").toString());
        String orderStatus = params.get("orderStatus").toString();
        String orderNo = params.get("orderNo").toString();
        String start = params.get("startTime").toString();

        String end = params.get("startTime").toString();

        Date startDate = null;
        Date endDate = null;
        if (start != null && start != "" && start.length() != 0){
            long startTime = Long.parseLong(params.get("startTime").toString());
            startDate = new Date(startTime);
        }
        if (end != null && end != "" && end.length() != 0){
            long endTime = Long.parseLong(params.get("endTime").toString());
            endDate = new Date(endTime);
        }

        Page<OrderListDto> orderListDtoPage = new Page<>(page,limit);
        Page<OrderListDto> orderListDtoPage1 = this.orderService.queryDrugOrderByPage(orderListDtoPage,orderStatus,orderNo,startDate,endDate);
        List<OrderListDto> orderListDtos = orderListDtoPage1.getRecords();

        long total = orderListDtoPage1.getTotal();

        Map map = new HashMap();

        map.put("orderListDtos",orderListDtos);
        map.put("total",total);

        return new Result().ok(map);
    }

    @ApiOperation(value="退款申请查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前是第几页",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "limit",value ="每页显示多少条",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "refundStatus",value ="查询条件退款状态",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "orderNo",value ="查询条件订单编号",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "startTime",value ="查询条件开始时间(时间戳)",dataType ="date"),
            @ApiImplicitParam(paramType = "query",name = "endTime",value ="查询条件结束时间(时间戳)",dataType ="date"),
    }
    )
    @PostMapping("/refundOrderList")
    public Result refundOrderList(@RequestParam @ApiIgnore Map<String,Object> params){
        Integer page = Integer.parseInt(params.get("page").toString());
        Integer limit = Integer.parseInt(params.get("limit").toString());
        String refundStatus = params.get("refundStatus").toString();
        String orderNo = params.get("orderNo").toString();
        String start = params.get("startTime").toString();

        String end = params.get("startTime").toString();

        Date startDate = null;
        Date endDate = null;
        if (start != null && start != "" && start.length() != 0){
            long startTime = Long.parseLong(params.get("startTime").toString());
            startDate = new Date(startTime);
        }
        if (end != null && end != "" && end.length() != 0){
            long endTime = Long.parseLong(params.get("endTime").toString());
            endDate = new Date(endTime);
        }

        Page<RefundListDto> refundListDtoPage = new Page<>(page,limit);
        Page<RefundListDto> refundListDtoPage1 = this.orderService.queryRefundOrderByPage(refundListDtoPage,refundStatus,orderNo,startDate,endDate);
        List<RefundListDto> refundListDtos = refundListDtoPage1.getRecords();
        long total = refundListDtoPage1.getTotal();

        Map map = new HashMap();
        map.put("total",total);
        map.put("refundListDtos",refundListDtos);
        return new Result().ok(map);
    }



    @ApiOperation(value="查看订单详细接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "orderNo",value ="订单编号",dataType ="String"),
    }
    )
    @PostMapping("/drugOrderDetail")
    public Result drugOrderDetail(@RequestParam @ApiIgnore Map<String,Object> params){

        String orderNo = params.get("orderNo").toString();

        OrderDetailDto orderDetailDto = this.orderService.queryOrderDetailByOrderNo(orderNo);

        Map map = new HashMap();
        map.put("orderDetailDto",orderDetailDto);

        return new Result().ok(map);
    }

    @ApiOperation(value="退款详情接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "applicationNo",value ="服务编号",dataType ="String"),
    }
    )
    @PostMapping("/refundDetail")
    public Result refundDetail(@RequestParam @ApiIgnore Map<String,Object> params){

        String applicationNo = params.get("applicationNo").toString();

        RefundDetailDto refundDetailDto = this.orderService.queryRefundDetailByApplicationNo(applicationNo);

        Map map = new HashMap();
        map.put("refundDetailDto",refundDetailDto);

        return new Result().ok(map);
    }

    @ApiOperation(value="查询快递公司")
    @GetMapping("/queryExpress")
    public Result queryExpress(){

        List<ExpressDto> expressDtos = this.orderService.queryAllExpress();
        Map map = new HashMap();
        map.put("expressDtos",expressDtos);
        return new Result().ok(map);
    }

    @ApiOperation(value="订单发货接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "orderNo",value ="订单编号",dataType ="String"),
    }
    )
    @PostMapping("/orderShip")
    public Result orderShipment(@RequestParam @ApiIgnore Map<String,Object> params){

        String orderNo = params.get("orderNo").toString();


        Map map = new HashMap();


        return new Result().ok(map);
    }



}
