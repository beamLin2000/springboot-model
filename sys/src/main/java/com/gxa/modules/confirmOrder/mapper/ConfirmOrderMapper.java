package com.gxa.modules.confirmOrder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.confirmOrder.dto.ConfirmShoppCart;
import com.gxa.modules.confirmOrder.dto.OrderDto;
import com.gxa.modules.confirmOrder.entity.Address;
import com.gxa.modules.confirmOrder.entity.ConfirmOrder;
import com.gxa.modules.confirmOrder.entity.DrugUserInformation;
import com.gxa.modules.confirmOrder.entity.Order;
import com.gxa.modules.shoppingCart.dto.ShoppingCartDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Mapper
public interface ConfirmOrderMapper extends BaseMapper<OrderDto> {
    int addAddress(Address address);
    int addConfirmOrder(@Param("confirmOrders") ConfirmOrder confirmOrders,@Param("orderNum") String orderNum);
    int addDrugUserInformation(DrugUserInformation drugUserInformation);
    int addOrder(Order order);

    List<ConfirmShoppCart> shoppingAll();

}
