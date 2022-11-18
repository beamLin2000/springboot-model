package com.gxa.modules.myInfo.service;

import com.gxa.modules.myInfo.entity.OrderTemp;
import com.gxa.modules.myInfo.entity.WaitPayOrder;

import java.util.List;

public interface MyOrderService {
    List<WaitPayOrder> queryOrder(Integer id,String status);
    List<WaitPayOrder> queryOrderByName(Integer id,String name);

}
