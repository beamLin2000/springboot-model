package com.gxa.modules.homepage.controller;

import com.gxa.common.utils.Result;
import com.gxa.modules.homepage.entity.*;
import com.gxa.modules.homepage.service.GoodsAndSalesService;
import com.gxa.modules.homepage.service.PandentService;
import com.gxa.modules.homepage.service.PendingService;
import com.gxa.modules.homepage.service.TransactionStatisticsService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
@Api(tags = "后台首页接口")
@RestController
public class HomePageController {
    @Autowired
    private GoodsAndSalesService goodsAndSalesService;
    @Autowired
    private PendingService pendingService;
    @Autowired
    private PandentService pandentService;
    @Autowired
    private TransactionStatisticsService transactionStatisticsService;

    @ApiOperation(value="商品总量的查询接口")
    @GetMapping("/getgetGoodsAndSales")
    public Result<GoodsAndSales> getGoodsAndSales(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date today = calendar.getTime();
        long time = today.getTime();
        time = time+24*60*60*1000;
        Date tomorrow = new Date(time);
        GoodsAndSales goodsAndSales = this.goodsAndSalesService.queryGoodsAndSalesByToday(today,tomorrow);
        return new Result<GoodsAndSales>().ok(goodsAndSales);
    }

    @ApiOperation(value="待处理事务的查询接口")
    @GetMapping("/getPending")
    public Result<Pending> getPending(){
        Pending pending = this.pendingService.queryPending();
        return new Result<Pending>().ok(pending);
    }
    @ApiOperation(value="商品总览和用户总览的查询接口")
     @GetMapping("/getPandent")
    public Result getPandent(){
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
//获取今天0点
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date today = calendar.getTime();
        long time = today.getTime();
        time=time+24*60*60*1000;
        Date tomorrow = new Date(time);
        time=time-2*24*60*60*1000;
        Date yesterday = new Date(time);
        Map map = this.pandentService.queryCommodityAndUserPandent(today, yesterday, tomorrow, firstday, lastday);
        return new Result().ok(map);
    }

    @ApiOperation(value="交易统计的查询接口")
     @GetMapping("/getTransactionStatistics")
    public Result getTransactionStatistics(@RequestParam("date") String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date today = null;
        try {

            System.out.println(date
            );
            today = format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println(today);
        List<TransactionStatistics> transactionStatistics = this.transactionStatisticsService.queryTransaction(today);
        Map map = new HashMap();
        map.put("list",transactionStatistics);
        return new Result().ok(map);
    }

}
