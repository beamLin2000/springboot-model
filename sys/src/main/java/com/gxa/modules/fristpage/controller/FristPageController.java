package com.gxa.modules.fristpage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gxa.common.utils.Result;
import com.gxa.modules.fristpage.entity.Cart;
import com.gxa.modules.fristpage.entity.Goods;
import com.gxa.modules.fristpage.entity.GoodsInfo;
import com.gxa.modules.fristpage.entity.LimitedTimeGoods;
import com.gxa.modules.fristpage.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(tags = "前台首页接口")
@RestController
public class FristPageController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private PollGraphService pollGraphService;
    @Autowired
    private GoodsInfoService goodsInfoService;
    @Autowired
    private CartService cartService;
    @Autowired
    private LimitedTimeGoodsService limitedTimeGoodsService;
    @ApiOperation(value="搜索接口")
    @GetMapping("/search")
    public Result search(@RequestParam("key") String key){
        Map map = this.goodsService.queryByStr(key);
        return new Result().ok(map);
    }
    @ApiOperation(value="轮插图接口")
    @GetMapping("/pollingGraph")
    public Result pollingGraph(){
        List list = this.pollGraphService.queryPollGraph();
        return new Result().ok(list);
    }
    @ApiOperation(value="对症找药接口")
    @GetMapping("/findMedicineForSymptoms")
    public Result findMedicineForSymptoms(@RequestParam("str") String str){
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        List<Goods> goods = this.goodsService.getBaseMapper().selectList(wrapper.like("main_function",str));
        return new Result().ok(goods);
    }
    @ApiOperation(value="限时购接口")
    @GetMapping("/limitedTimePurchase")
    public Result limitedTimePurchase(){
        List<LimitedTimeGoods> queryoen = this.limitedTimeGoodsService.queryoen();

        return new Result().ok(queryoen);
    }
    @ApiOperation(value="好物推荐接口")
    @GetMapping("/goodMedicineRecommendation")
    public Result goodMedicineRecommendation(){
        List<Goods> goods = this.goodsService.queryRecommed();
        return new Result().ok(goods);
    }
    @ApiOperation(value="药品详情接口")
    @GetMapping("/drugDetails")
    public Result drugDetails(@RequestParam("str") String str){
        // 获取当月第一天
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstday = calendar.getTime();
// 获取当月最后一天
        calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        Date lastday = calendar.getTime();
        System.out.println(str);
        GoodsInfo goodsInfo = this.goodsInfoService.queryByName(str, firstday, lastday);
        return new Result().ok(goodsInfo);
    }
    @ApiOperation(value="药品详情里加入清单接口")
    @PostMapping("/addToList")
    public Result addToList(@RequestBody Cart cart){
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        Cart maxidCart = this.cartService.getOne(wrapper.orderByDesc("id").last("limit 1"));
        cart.setId(maxidCart.getId()+1);
        this.cartService.save(cart);
        return new Result().ok();
    }
    @ApiOperation(value="药品详情里清单接口")
    @GetMapping("/list")
    public Result list(){

        List list = new ArrayList();
        list.add("sss");
        return new Result().ok(list);
    }


}
