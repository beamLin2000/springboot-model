package com.gxa.modules.sys.service.promotion.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Query;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.*;

import com.gxa.modules.sys.mapper.backStage.promotion.couponManagement.CouponMapper;
import com.gxa.modules.sys.service.promotion.CouponManagementService;
import com.gxa.modules.sys.service.promotion.CouponUsageInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author LXD
 * @Date 2022/11/11 15:40
 * @Version 1.0
 */
@Service
public class CouponManagementServiceImpl extends ServiceImpl<CouponMapper, CouponManagement> implements CouponManagementService {

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private CouponUsageInfoService couponUsageInfoService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 根据条件查询，分页查询，模糊查询
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils search(Map<String, Object> params) {
        //获取条件
        String couponName = (String) params.get("couponName");
        String couponType = (String) params.get("couponType");

        IPage<CouponManagement> couponManagementIPage = this.page(new Query<CouponManagement>().getPage(params),
                new QueryWrapper<CouponManagement>().like(StringUtils.isNotEmpty(couponName), "coupon_name", couponName)
                        .like(StringUtils.isNotEmpty(couponType), "coupon_type", couponType).orderByDesc("id"));
        return new PageUtils(couponManagementIPage);
    }

    @Override
    public CouponManagementAll searchById(String id) {
        CouponManagementAll couponManagementAll = couponMapper.queryById(id);
        return couponManagementAll;
    }

    /**
     * 删除选中的数据
     * @param ids
     */
    public void deleteByIds(List<String> ids) {
        int i = this.couponMapper.deleteBatchIds(ids);
    }


    @Override
    public void add(CouponManagerAddAndEdit couponManagementAll) {


        if (couponManagementAll.getUsableGoods().equals("指定商品")) {

            //调用查看商品的接口
            //调用商品的搜索接口
            //获取到页面传来的药品添加
            List<CouponAddDrug> specifyProduct = couponManagementAll.getSpecifyProduct();

            couponMapper.couponAddDrug(specifyProduct,couponManagementAll.getId());


        }else if (couponManagementAll.getUsableGoods().equals("指定分类")){

            //添加分类的优惠券
            List<CouponAddClass> specifyClassification = couponManagementAll.getSpecifyClassification();
            couponMapper.couponAddClass(specifyClassification,couponManagementAll.getId());

        }
        //保存优惠券的表
        this.couponMapper.add(couponManagementAll);
        updateStatus(couponManagementAll.getId());

    }

    /**
     * 编辑前的查询
     * @param id
     */
    @Override
    public CouponManagementAll selectById(String id) {
        CouponManagementAll couponManagementAll = couponMapper.queryById(id);

        if (couponManagementAll.getUsableGoods().equals("指定商品")){
            List<CouponAddDrug> couponAddDrug = this.couponMapper.selectDrugById(id);
            couponManagementAll.setSpecifyProduct(couponAddDrug);
        }else if (couponManagementAll.getUsableGoods().equals("指定分类")){
            List<CouponAddClass> couponAddClass = this.couponMapper.selectClassById(id);
            couponManagementAll.setSpecifyClassification(couponAddClass);
        }
        return couponManagementAll;
    }

    /**
     * 编辑
     * @param couponManagementAll
     */
    @Override
    public void edit(CouponManagementAll couponManagementAll) {

        //编辑
        this.couponMapper.edit(couponManagementAll);

        if (couponManagementAll.getUsableGoods().equals("指定商品")){
            //删除添加过的药品
            this.couponMapper.deleteDrugById(couponManagementAll.getId());
        }else if(couponManagementAll.getUsableGoods().equals("指定分类")){
            //删除添加过的分类
            this.couponMapper.deleteClassById(couponManagementAll.getId());
        }

        //当他点击保存的时候
        if (couponManagementAll.getUsableGoods().equals("指定商品")) {

            //获取到页面传来的药品添加
            List<CouponAddDrug> specifyProduct = couponManagementAll.getSpecifyProduct();
            couponMapper.couponAddDrug(specifyProduct,couponManagementAll.getId());

        }else if (couponManagementAll.getUsableGoods().equals("指定分类")){

            //添加分类的优惠券
            List<CouponAddClass> specifyClassification = couponManagementAll.getSpecifyClassification();
            couponMapper.couponAddClass(specifyClassification,couponManagementAll.getId());
        }
    }


    /**
     * 将失效时间放入mq中，然后判断是否已过期
     * @param id
     */
    @Override
    public void updateStatus(String id) {
        CouponManagementAll couponManagementAll = this.couponMapper.queryById(id);
        System.out.println("con=" + couponManagementAll );
        String expirationDate = couponManagementAll.getExpirationDate();
        System.out.println("ex=" + expirationDate);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] split = expirationDate.split(",");
        System.out.println("split=" + split[0]);
        //开始时间
        String startTime = split[0];
        //结束时间
        String endTime = split[1];
        MessageProperties messageProperties = new MessageProperties();
        try {
            Date sTime = simpleDateFormat.parse(startTime);
            Date eTime = simpleDateFormat.parse(endTime);
            Long time =  eTime.getTime() - sTime.getTime();

            System.out.println("time=" + String.valueOf(time));
            //将有效期时间放入mq中的message
//            messageProperties.setExpiration("10000");
            messageProperties.setExpiration(String.valueOf(time));
            Message message = new Message(couponManagementAll.getId().getBytes(), messageProperties);
            this.rabbitTemplate.convertAndSend("123","123",message);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "789")
    public void listener(Message message){
        String id = new String(message.getBody());
        System.out.println("mes=" + message);
        System.out.println("mesid=" + id);
        CouponManagementAll couponManagementAll = this.couponMapper.queryById(id);
        couponManagementAll.setStatus("已过期");
        this.couponMapper.edit(couponManagementAll);
    }

    @Override
    public  String addPre() {
        String s = UUID.randomUUID().toString();
        return s;
    }


}
