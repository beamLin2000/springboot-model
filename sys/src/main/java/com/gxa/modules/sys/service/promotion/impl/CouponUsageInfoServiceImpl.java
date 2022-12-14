package com.gxa.modules.sys.service.promotion.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Query;
import com.gxa.modules.goods.goodsEntity.Drug;
import com.gxa.modules.login.entity.User;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponAddDrug;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponManagement;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponManagementAll;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponUsageInformation;
import com.gxa.modules.sys.mapper.backStage.promotion.couponManagement.CouponInfoMapper;
import com.gxa.modules.sys.mapper.backStage.promotion.couponManagement.CouponMapper;
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
import java.util.*;

/**
 * @Author LXD
 * @Date 2022/11/12 9:41
 * @Version 1.0
 */
@Service
public class CouponUsageInfoServiceImpl extends ServiceImpl<CouponInfoMapper, CouponUsageInformation> implements CouponUsageInfoService {


    @Autowired
    private CouponInfoMapper couponInfoMapper;

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @param params
     * @return
     */
    @Override
    public PageUtils searchCouponUsageInfo(Map<String, Object> params) {
        String id = (String) params.get("id");
        IPage<CouponUsageInformation> page = this.page(new Query<CouponUsageInformation>().getPage(params),
                new QueryWrapper<CouponUsageInformation>().eq(StringUtils.isNotEmpty(id), "coupon_management_id", id));
        return new PageUtils(page);
    }

    /**
     * ???????????????
     *
     * @param couponUsageInformation
     */
    @Override
    public void addCoupons(CouponUsageInformation couponUsageInformation) {


        //?????????????????????
        List<CouponManagement> couponManagements = couponUsageInformation.getCouponManagements();
        //????????????
        List<User> users = couponUsageInformation.getUsers();

        //??????????????????addpre????????????id
        //??????
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        couponUsageInformation.setCollectionTime(format);

        //??????
        couponUsageInformation.setCurrentState("?????????");

        //??????
        this.couponInfoMapper.addCoupons(couponManagements, users, couponUsageInformation);

    }


    /**
     * ???????????????
     * @param couponUsageInformation
     */
    @Override
    public void editCoupons(CouponUsageInformation couponUsageInformation) {
        List<CouponManagement> couponManagements = couponUsageInformation.getCouponManagements();
        for (int i = 0; i < couponManagements.size(); i++) {
            if (couponManagements.get(i).getStatus().equals("?????????")){
                couponUsageInformation.setCurrentState("?????????");
                this.couponInfoMapper.editCoupons(couponUsageInformation);
            }

        }
        couponUsageInformation.setCurrentState("?????????");
        this.couponInfoMapper.editCoupons(couponUsageInformation);
    }

    /**
     * ?????????????????????
     *
     * @param id
     * @return
     */
    @Override
    public List<CouponUsageInformation> queryAllById(String id) {
        List<CouponUsageInformation> couponUsageInformations = this.couponInfoMapper.queryAllById(id);

        return couponUsageInformations;
    }


    /**
     * ????????????????????????
     *
     * @param drugs
     * @param userId
     * @return
     */
    @Override
    public List<CouponManagementAll> queryUseCoupon(List<Drug> drugs, String userId) {
        List<CouponManagementAll> couponManagementAllList = new ArrayList<>();

        List<CouponManagementAll> couponManagementAlls = this.couponInfoMapper.queryUseCoupon(userId);
        System.out.println("???????????????=" + couponManagementAlls);
        for (int i = 0; i < couponManagementAlls.size(); i++) {
            List<String> drugNames = couponManagementAlls.get(i).getDrugNames();
            System.out.println("????????????????????????=" + drugNames);
            for (int j = 0; j < drugNames.size(); j++) {
                for (int k = 0; k < drugs.size(); k++) {
                    if (drugNames.get(j).equals(drugs.get(k).getDrugName())) {
                        couponManagementAllList.add(couponManagementAlls.get(i));
                    }
                }
            }
        }
        return couponManagementAllList;
    }
}


