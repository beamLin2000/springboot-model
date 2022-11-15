package com.gxa.modules.confirmOrder.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.modules.confirmOrder.dto.OrderDto;
import com.gxa.modules.confirmOrder.entity.Address;
import com.gxa.modules.confirmOrder.entity.ConfirmOrder;
import com.gxa.modules.confirmOrder.entity.DrugUserInformation;
import com.gxa.modules.confirmOrder.entity.Order;
import com.gxa.modules.confirmOrder.mapper.ConfirmOrderMapper;
import com.gxa.modules.confirmOrder.service.ConfirmOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ConfirmOrderServiceImpl extends ServiceImpl<ConfirmOrderMapper, OrderDto> implements ConfirmOrderService {

    @Autowired
    private ConfirmOrderMapper confirmOrderMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addOrder(Address address, List<ConfirmOrder> confirmOrders, DrugUserInformation drugUserInformation, Order order) {
        int plan = this.confirmOrderMapper.addOrder(order);

        if (plan != 1){
            throw new RuntimeException("插入失败");
        }
        for (ConfirmOrder confirmOrder:confirmOrders){
            this.confirmOrderMapper.addConfirmOrder(confirmOrder,order.getOrderNo());
        }


    }
}
