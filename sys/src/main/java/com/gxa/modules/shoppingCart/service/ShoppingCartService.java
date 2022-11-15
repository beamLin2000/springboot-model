package com.gxa.modules.shoppingCart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.modules.shoppingCart.dto.ShoppingCartDto;
import com.gxa.modules.shoppingCart.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService extends IService<ShoppingCart> {
    List<ShoppingCart>queryAll();
    void updateShopping(ShoppingCart shoppingCart);
    List<ShoppingCartDto>querySpCart(Integer userId);
    void deleteShoppingBatch(List<ShoppingCart> shoppingCarts);


}
