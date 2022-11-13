package com.gxa.modules.confirmOrder.controller;


import com.gxa.common.utils.Result;
import com.gxa.modules.confirmOrder.dto.OrderDto;
import com.gxa.modules.confirmOrder.entity.Prescription;
import com.gxa.modules.confirmOrder.service.ConfirmOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "确认订单")
@RestController
@Slf4j
public class ConfirmOrderController {

    @Autowired
    private ConfirmOrderService confirmOrderService;


    @ApiOperation("确认订单接口")
    @GetMapping("/confirmOrder/from")
    public Result confirmOrder(){
        OrderDto form = this.confirmOrderService.form();
        return new Result().ok(form);
    }

    @ApiOperation("添加处方")
    @PostMapping("/confirmOrder/add")
    public Result addPrescription(@RequestBody Prescription prescription){

        return new Result().ok();
    }
}
