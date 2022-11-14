package com.gxa.modules.myInfo.controller;

import com.gxa.common.utils.Result;
import com.gxa.modules.confirmOrder.dto.OrderDto;
import com.gxa.modules.login.entity.User;
import com.gxa.modules.login.redis.SysUserRedis;
import com.gxa.modules.login.service.UserTokenService;
import com.gxa.modules.myInfo.entity.MyInfo;
import com.gxa.modules.myInfo.entity.ShipToAddress;
import com.gxa.modules.myInfo.entity.WaitPayOrder;
import com.gxa.modules.myInfo.service.CancelService;
import com.gxa.modules.myInfo.service.MyInfoService;
import com.gxa.modules.myInfo.service.MyOrderService;
import com.gxa.modules.myInfo.service.NewAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Api(tags = "我的")
@RestController
@Slf4j
public class MyInfoCortroller {
    @Autowired
    private UserTokenService userTokenService;
    @Autowired
    private MyInfoService myInfoService;
    @Autowired
    private MyOrderService myOrderService;
    @Autowired
    private CancelService cancelService;
    @Autowired
    private NewAddressService newAddressService;
    @ApiOperation("我的基本信息")
    @GetMapping("/myinfo")
    public Result confirmOrder(HttpServletRequest request){
        String token = request.getHeader("token");
        User user = this.userTokenService.validateToken(token);
        MyInfo myInfo = this.myInfoService.queryMyinfo(user.getId());
        myInfo.setHeadPortrait(user.getHeadPortrait());
        myInfo.setPhone(user.getPhoneNumber());
        return new Result().ok(myInfo);
    }
    @ApiOperation("我的订单")
    @GetMapping("/myorder")
    public Result myOrder(HttpServletRequest request,@RequestParam("status") String status){
        String token = request.getHeader("token");
        User user = this.userTokenService.validateToken(token);
        log.info(status);
        System.out.println(user);
        List<WaitPayOrder> waitPayOrders = this.myOrderService.queryOrder(user.getId(), status);
        return new Result().ok(waitPayOrders);
    }
    @ApiOperation("取消订单")
    @GetMapping("/cancelorder")
    public Result cancelOrder(HttpServletRequest request,@RequestParam("orderNo") String orderNo){

        boolean b = this.cancelService.updataStatus(orderNo);
        if (b){
            return new Result().ok();
        }
       return new Result().error(408,"取消失败");
    }
    @ApiOperation("申请退款")
    @GetMapping("/refundOrder")
    public Result refundOrder(HttpServletRequest request,@RequestParam("orderNo") String orderNo,@RequestParam("reason") String reason){


        boolean refund = this.cancelService.refund(orderNo, reason);
        if (refund){
            return new Result().ok();
        }
        return new Result().error(408,"申请失败");
    }
    @ApiOperation("确认收货")
    @GetMapping("/confirmOrder")
    public Result confirmOrder(HttpServletRequest request,@RequestParam("orderNo") String orderNo){


        boolean b = this.cancelService.confirmOrder(orderNo);
        if (b){
            return new Result().ok();
        }
        return new Result().error(408,"失败");
    }
    @ApiOperation("删除订单")
    @GetMapping("/delOrder")
    public Result delOrder(HttpServletRequest request,@RequestParam("orderNo") String orderNo){


        boolean b = this.cancelService.delOrder(orderNo);
        if (b){
            return new Result().ok();
        }
        return new Result().error(408,"申请失败");
    }
    @ApiOperation("新增地址")
    @GetMapping("/newAddress")
    public Result newAddress(HttpServletRequest request, @RequestBody ShipToAddress shipToAddress){

        String token = request.getHeader("token");
        User user = this.userTokenService.validateToken(token);
        shipToAddress.setId(UUID.randomUUID().toString());
        shipToAddress.setUserId(user.getId());
        shipToAddress.setVersion(1);
        boolean b = this.newAddressService.addAddress(shipToAddress);
        if (b){
            return new Result().ok();
        }
        return new Result().error(408,"申请失败");
    }
    @ApiOperation("地址管理")
    @GetMapping("/allAddress")
    public Result allAddress(HttpServletRequest request){
        String token = request.getHeader("token");
        User user = this.userTokenService.validateToken(token);
        List<ShipToAddress> shipToAddresses = this.newAddressService.allAddress(user.getId().toString());

        return new Result().ok(shipToAddresses);
    }
}
