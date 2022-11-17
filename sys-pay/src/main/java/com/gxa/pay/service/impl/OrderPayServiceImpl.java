package com.gxa.pay.service.impl;

import com.gxa.pay.mapper.OrderPayMapper;
import com.gxa.pay.service.OrderPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderPayServiceImpl implements OrderPayService {
    @Autowired
    private OrderPayMapper orderPayMapper;
    @Override
    public Boolean updataForPay(String orderNo){
        Date date = new Date();
        Boolean aBoolean = this.orderPayMapper.updataForPay(date,orderNo);
        if (!aBoolean){
            throw new RuntimeException("支付失败");
        }
        this.orderPayMapper.updatePrescription(orderNo,5);
        return aBoolean;
    }
}
