package com.gxa.pay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.baomidou.mybatisplus.extension.api.R;
import com.gxa.pay.config.AliPayProperties;
import com.gxa.pay.entity.DrugStockAndSalesVolume;
import com.gxa.pay.entity.WaitPayOrder;
import com.gxa.pay.service.DrugStockAndSalesVolumeService;
import com.gxa.pay.service.OrderPayService;
import com.ijpay.alipay.AliPayApi;
import com.ijpay.alipay.AliPayApiConfig;
import com.ijpay.alipay.AliPayApiConfigKit;
import com.mysql.cj.xdevapi.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 支付宝支付
 *
 * @author shelei
 */
@Api(tags = "支付接口")
@RestController
@RequestMapping("/pay/alipay")
public class AliPayController extends AbstractAliPayApiController {
    @Autowired
    private DrugStockAndSalesVolumeService drugStockAndSalesVolumeService;
    @Autowired
    private AliPayProperties properties;
    @Autowired
    private OrderPayService orderPayService;
    @Override
    public AliPayApiConfig getApiConfig() throws AlipayApiException {
        AliPayApiConfig aliPayApiConfig;
        try {
            aliPayApiConfig = AliPayApiConfigKit.getApiConfig(properties.getAppId());
        } catch (Exception e) {
            aliPayApiConfig = AliPayApiConfig.builder()
                    .setAppId(properties.getAppId())
                    .setAliPayPublicKey(properties.getPublicKey())
//                .setAppCertPath(properties.getAppCertPath())
//                .setAliPayCertPath(properties.getAliPayCertPath())
//                .setAliPayRootCertPath(properties.getAliPayRootCertPath())
                    .setCharset("UTF-8")
                    .setPrivateKey(properties.getPrivateKey())
                    .setServiceUrl(properties.getServerUrl())
                    .setSignType("RSA2")
                    // 普通公钥方式
                    .build();
            // 证书模式
//                .buildByCert();
        }
        return aliPayApiConfig;
    }

    /**
     * Web支付
     */
    @RequestMapping(value = "/webPay")
    public void webPay(HttpServletResponse response, WaitPayOrder order) throws Exception {
        //demo
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(order.getOrderNo());
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        model.setTotalAmount(order.getOrderAmount().toString());
        model.setSubject("待支付订单");

        try {
            AliPayApi.tradePage(response, model, properties.getNotifyUrl(), properties.getReturnUrl());
            this.orderPayService.updataForPay(order.getOrderNo());


            List<DrugStockAndSalesVolume> drugStockAndSalesVolumes = this.drugStockAndSalesVolumeService.drugList(order.getOrderNo());
            for (DrugStockAndSalesVolume drugStockAndSalesVolume:drugStockAndSalesVolumes){
                this.drugStockAndSalesVolumeService.updateStockAndSalesVolume(
                        drugStockAndSalesVolume.getStock() + drugStockAndSalesVolume.getQuantity(),
                        drugStockAndSalesVolume.getSalesVolume() + drugStockAndSalesVolume.getQuantity(),
                        drugStockAndSalesVolume.getId() + drugStockAndSalesVolume.getQuantity());
            }


        }catch (Exception e){
            throw new RuntimeException("支付失败");
        }
    }


    /**
     * 支付宝异步通知接口
     */
    @PostMapping("notify_url")
    public String notifyUrl(HttpServletRequest request) throws Exception {
        //支付宝异步通知内容
        Map<String, String> params = AliPayApi.toMap(request);

        //秘钥模式
        boolean verifyResult = AlipaySignature.rsaCheckV1(params, properties.getPublicKey(), "UTF-8", "RSA2");
        //证书模式
//        boolean verifyResult = AlipaySignature.rsaCertCheckV1(params, properties.getAliPayCertPath(), "UTF-8", "RSA2");

        //验签失败
        if (!verifyResult) {
            return "failure";
        }

        //记录支付宝回调信息

        //如果支付宝返回成功更新订单状态

        //查询订单信息 根据支付宝的回调，去查询该订单的信息

        //重复通知，不再处理 如果订单状态 是已经完成
//        if(order.getStatus() == OrderStatusEnum.FINISH.getValue()){
//            return "success";
//        }


        return "success";
    }





}

 
