package com.gxa.modules.confirmOrder.controller;


import com.gxa.common.utils.Result;
import com.gxa.modules.confirmOrder.dto.OrderDto;
import com.gxa.modules.confirmOrder.entity.*;
import com.gxa.modules.confirmOrder.form.OrderForm;
import com.gxa.modules.confirmOrder.service.ConfirmOrderService;
import com.gxa.modules.login.entity.User;
import com.gxa.modules.login.service.UserTokenService;
import com.gxa.modules.myInfo.service.CancelService;
import com.gxa.modules.shoppingCart.dto.ShoppingCartDto;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Api(tags = "确认订单(前台)")
@RestController
@Slf4j
public class ConfirmOrderController {

    @Autowired
    private ConfirmOrderService confirmOrderService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private CancelService cancelService;

    @Autowired
    private UserTokenService userTokenService;
    @ApiOperation("确认订单接口")
    @PostMapping("/confirmOrder/from00001")
    public Result confirmOrder(@RequestBody OrderForm form, HttpServletRequest request){
        Date date01 = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        //获取String类型的时间
        String createdate = sdf.format(date01);
        System.out.println("createdate"+createdate);
        int num = (int) ((Math.random() * 9 + 1) * 1000);
        String no = createdate + "" + num;

//        System.out.println("num=================="+num);
//        System.out.println("order-----------------------"+form.getOrder());
//        System.out.println("address==========================="+form.getAddress());
//        System.out.println("confirmOrder==========================="+form.getConfirmOrder());
//        System.out.println("drugUserInformation==========================="+form.getDrugUserInformation());

        String token = request.getHeader("token");
        User user = this.userTokenService.validateUserToken(token);
        form.getOrder().setUserId(user.getId());
        form.getOrder().setOrderNo(no);
        form.getOrder().setOrderStatus("待支付");
        form.getOrder().setAddressId(form.getAddress().getId());
        form.getOrder().setPrescriptionId(form.getDrugUserInformation().getId());
        Date date = new Date();
        date.setTime(date.getTime());
        form.getOrder().setOrderCreateTime(date);
//        System.out.println("drugUserInformation==========================="+form.getDrugUserInformation());
//        System.out.println("order-----------------------"+form.getOrder());

        this.confirmOrderService.addOrder(form.getAddress(),form.getConfirmOrder(),form.getDrugUserInformation(),form.getOrder());

        this.rabbitTemplate.convertAndSend("order-exchange","order-key",no);
        return new Result().ok();
    }

    @RabbitListener(queues = "dead-queue")
    public void cancel(String orderNo){
        this.cancelService.updataStatus(orderNo);
    }


}
