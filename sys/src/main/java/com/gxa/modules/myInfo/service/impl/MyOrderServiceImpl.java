package com.gxa.modules.myInfo.service.impl;

import com.gxa.modules.myInfo.entity.OrderTemp;
import com.gxa.modules.myInfo.entity.WaitPayOrder;
import com.gxa.modules.myInfo.mapper.MyOrderMapper;
import com.gxa.modules.myInfo.service.MyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MyOrderServiceImpl implements MyOrderService {
    @Autowired
    private MyOrderMapper myOrderMapper;
    @Override
    public List<WaitPayOrder> queryOrder(Integer id, String status) {
        List<WaitPayOrder> waitPayOrders = this.myOrderMapper.queryByStatus(id,status);
        for (WaitPayOrder order :
                waitPayOrders) {
            List<OrderTemp> orderTemps = this.myOrderMapper.queryByIdAndStatus(order.getOrderNo());
            order.setOrderTemps(orderTemps);
        }
        return waitPayOrders;
    }

    @Override
    public List<WaitPayOrder> queryOrderByName(Integer userid,String name) {
        List<Integer> integers = this.myOrderMapper.queryOrderIdByName(name);
        List<String> strings = new ArrayList<>();
        List<WaitPayOrder> waitPayOrders = new ArrayList<>();
        for (Integer id1 :
                integers) {
           String  string = this.myOrderMapper.queryOrderNo(userid,id1);
           strings.add(string);
        }
        for (String orderNo :
                strings) {
            if (orderNo!=null&&!orderNo.equals("")){
                WaitPayOrder waitPayOrder = this.myOrderMapper.queryByname(orderNo);
                waitPayOrders.add(waitPayOrder);
            }
        }
        for (WaitPayOrder order :
                waitPayOrders) {
            List<OrderTemp> orderTemps = this.myOrderMapper.queryByIdAndStatus(order.getOrderNo());
            order.setOrderTemps(orderTemps);
        }
        return waitPayOrders;
    }
}
