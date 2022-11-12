package com.gxa.modules.confirmOrder.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.modules.confirmOrder.dto.OrderDto;
import com.gxa.modules.confirmOrder.entity.Order;
import com.gxa.modules.confirmOrder.mapper.ConfirmOrderMapper;
import com.gxa.modules.confirmOrder.service.ConfirmOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfirmOrderServiceImpl extends ServiceImpl<ConfirmOrderMapper, OrderDto> implements ConfirmOrderService {
    @Override
    public OrderDto form() {
        Wrapper<OrderDto> wrapper = new QueryWrapper<>();
        OrderDto orderDto = this.baseMapper.selectOne(wrapper);
        return orderDto;
    }
}
