package com.gxa.modules.confirmOrder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.modules.confirmOrder.dto.OrderDto;
import com.gxa.modules.confirmOrder.entity.Order;

import java.util.List;

public interface ConfirmOrderService extends IService<OrderDto> {
    OrderDto form();
}
