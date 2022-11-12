package com.gxa.modules.shoppingCart.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.modules.shoppingCart.dto.ShoppingCartDto;
import com.gxa.modules.shoppingCart.entity.ShoppingCart;
import com.gxa.modules.shoppingCart.mapper.ShoppingMapper;
import com.gxa.modules.shoppingCart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingMapper, ShoppingCart> implements ShoppingCartService{

    @Autowired
    private ShoppingMapper shoppingMapper;

    @Override
    public List<ShoppingCart> queryAll() {
        List<ShoppingCart> shoppingCarts = this.baseMapper.selectList(null);
        return shoppingCarts;
    }

    @Override
    public void updateShopping(ShoppingCart shoppingCart) {
        UpdateWrapper<ShoppingCart>wrapper = new UpdateWrapper<>();
        wrapper.eq("id",shoppingCart.getId()).set(null,shoppingCart);
        this.baseMapper.updateById(shoppingCart);
    }

    @Override
    public List<ShoppingCartDto> querySpCart(Integer userId) {
        List<ShoppingCartDto> ShoppingCartDto = this.shoppingMapper.querySpCart(userId);
        return ShoppingCartDto;
    }

    @Override
    public void deleteShoppingBatch(List<ShoppingCart> shoppingCarts) {
        this.shoppingMapper.deleteShoppingBatch(shoppingCarts);

    }
}
