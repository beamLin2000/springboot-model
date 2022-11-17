package com.gxa.pay.service;

import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface OrderPayService {
    Boolean updataForPay(String orderNo);

}
