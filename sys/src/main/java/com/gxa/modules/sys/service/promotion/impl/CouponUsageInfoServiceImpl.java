package com.gxa.modules.sys.service.promotion.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Query;
import com.gxa.modules.goods.goodsEntity.Drug;
import com.gxa.modules.login.entity.User;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponManagement;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponManagementAll;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponUsageInformation;
import com.gxa.modules.sys.mapper.backStage.promotion.couponManagement.CouponInfoMapper;
import com.gxa.modules.sys.service.promotion.CouponUsageInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils searchCouponUsageInfo(Map<String, Object> params) {
        String id = (String) params.get("id");
        IPage<CouponUsageInformation> page = this.page(new Query<CouponUsageInformation>().getPage(params),
                new QueryWrapper<CouponUsageInformation>().eq(StringUtils.isNotEmpty(id),"id",id));
        return new PageUtils(page);
    }

    /**
     * 添加优惠券
     * @param couponUsageInformation
     */
    @Override
    public void addCoupons(CouponUsageInformation couponUsageInformation) {


        //获取优惠券种类
        List<CouponManagement> couponManagements = couponUsageInformation.getCouponManagements();
        //获取用户
        List<User> users = couponUsageInformation.getUsers();

        //这里调用这个addpre方法生成id
        //时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        couponUsageInformation.setCollectionTime(format);

        //状态
        couponUsageInformation.setCurrentState("未使用");

        //添加
        this.couponInfoMapper.addCoupons(couponManagements,users,couponUsageInformation);

    }



    @Override
    public void editCoupons(CouponUsageInformation couponUsageInformation) {
        this.couponInfoMapper.editCoupons(couponUsageInformation);
    }


    /**
     * 查看所有优惠券
     * @param id
     * @return
     */
    @Override
    public List<CouponUsageInformation> queryAllById(String id) {
        List<CouponUsageInformation> couponUsageInformations = this.couponInfoMapper.queryAllById(id);

        return couponUsageInformations;
    }


    /**
     * 查询可用的优惠券
     * @param drugs
     * @param userId
     * @return
     */
    @Override
    public List<CouponManagementAll> queryUseCoupon(List<Drug> drugs , String userId) {
        List<CouponManagementAll> couponManagementAllList = new ArrayList<>();

        List<CouponManagementAll> couponManagementAlls = this.couponInfoMapper.queryUseCoupon(userId);
        for (int i = 0; i < couponManagementAlls.size(); i++) {
            List<Drug> drugList = couponManagementAlls.get(i).getDrugList();
            for (int j = 0; j < drugList.size(); j++) {
                for (int k = 0; k < drugs.size(); k++) {
                    if(drugList.get(j).equals(drugs.get(k))){
                        couponManagementAllList.add(couponManagementAlls.get(i));
                }
            }
        }
        }
        return couponManagementAllList;
    }


}
