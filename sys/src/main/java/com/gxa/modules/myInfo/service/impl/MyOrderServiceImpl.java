package com.gxa.modules.myInfo.service.impl;

import com.gxa.modules.myInfo.entity.OrderTemp;
import com.gxa.modules.myInfo.entity.WaitPayOrder;
import com.gxa.modules.myInfo.mapper.MyOrderMapper;
import com.gxa.modules.myInfo.service.MyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MyOrderServiceImpl implements MyOrderService {
    @Autowired
    private MyOrderMapper myOrderMapper;
    @Override
    public List<WaitPayOrder> queryOrder(Integer id, String status) {
        List<WaitPayOrder> waitPayOrders = this.myOrderMapper.queryByStatus(id, status);
        for (WaitPayOrder order :
                waitPayOrders) {
            List<OrderTemp> orderTemps = this.myOrderMapper.queryByIdAndStatus(order.getOrderNo());
            order.setOrderTemps(orderTemps);
        }
        return waitPayOrders;
    }
}
