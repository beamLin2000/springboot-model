package com.gxa.modules.confirmOrder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.modules.confirmOrder.dto.OrderDto;
import com.gxa.modules.confirmOrder.entity.Address;
import com.gxa.modules.confirmOrder.entity.ConfirmOrder;
import com.gxa.modules.confirmOrder.entity.DrugUserInformation;
import com.gxa.modules.confirmOrder.entity.Order;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface ConfirmOrderService extends IService<OrderDto> {
    void addOrder(Address address,List<ConfirmOrder> confirmOrders,DrugUserInformation drugUserInformation,Order order);
}
