package com.gxa.modules.shoppingCart.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.shoppingCart.dto.ShoppingCartDto;
import com.gxa.modules.shoppingCart.entity.Drug;
import com.gxa.modules.shoppingCart.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShoppingMapper extends BaseMapper<ShoppingCart> {
    List<ShoppingCartDto>querySpCart(Integer userId);
    void deleteShoppingBatch(List<ShoppingCart> shoppingCarts);
//#{orderNo},#{shoppState},#{prescriptionId}
    void updateShoppingCar(@Param("shoppingCart") ShoppingCart shoppingCart);

}
