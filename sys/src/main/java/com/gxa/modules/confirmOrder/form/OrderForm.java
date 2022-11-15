package com.gxa.modules.confirmOrder.form;

import com.gxa.modules.confirmOrder.entity.Address;
import com.gxa.modules.confirmOrder.entity.ConfirmOrder;
import com.gxa.modules.confirmOrder.entity.DrugUserInformation;
import com.gxa.modules.confirmOrder.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderForm {
    private Address address;
    private List<ConfirmOrder> confirmOrder;
    private DrugUserInformation drugUserInformation;
    private Order order;
}
