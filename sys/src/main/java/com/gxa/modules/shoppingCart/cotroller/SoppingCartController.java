package com.gxa.modules.shoppingCart.cotroller;

import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.content.carousel.service.CarouselService;
import com.gxa.modules.shoppingCart.dto.ShoppingCartDto;
import com.gxa.modules.shoppingCart.entity.ShoppingCart;
import com.gxa.modules.shoppingCart.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

@Api(tags = "购物车（前台）")
@RestController
@Slf4j
public class SoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;


    /**
     *
     * @return
     */
    //购物车查询
    @ApiOperation("购物车查询接口XXXXXXX")
    @GetMapping("/shoppingcart/list")
    public Result shoppingcartList(){
        List<ShoppingCart> shoppingCarts = this.shoppingCartService.queryAll();
        return new Result().ok(shoppingCarts);
    }

    /**
     *
     * @param shoppingCart
     * @return
     */
    @ApiOperation("购物车编辑接口")
    @PutMapping("/shoppingcart/update")
    public Result shoppingcartUpdate(@RequestBody ShoppingCart shoppingCart){
        this.shoppingCartService.updateShopping(shoppingCart);
        return new Result().ok();
    }

    /**
     *
     * @param userId
     * @return
     */
    @ApiOperation("购物车详情接口")
    @GetMapping("/shoppingcart/list01")
    public Result shoppingCartSelect(@RequestParam("userId") Integer userId){
        List<ShoppingCartDto> shoppingCarts = this.shoppingCartService.querySpCart(userId);
        return new Result().ok(shoppingCarts);
    }

    /**
     *
     * @param shoppingCarts
     * @return
     */
    @ApiOperation("购物车删除接口")
    @DeleteMapping("/shoppingcart/delete")
    public Result shoppingCartDelete(@RequestBody List<ShoppingCart>shoppingCarts){
        this.shoppingCartService.deleteShoppingBatch(shoppingCarts);
        return new Result().ok();
    }

}
