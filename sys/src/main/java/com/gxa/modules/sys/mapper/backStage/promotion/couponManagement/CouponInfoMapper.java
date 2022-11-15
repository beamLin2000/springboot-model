package com.gxa.modules.sys.mapper.backStage.promotion.couponManagement;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.goods.goodsEntity.Drug;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponManagement;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponManagementAll;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponUsageInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author LXD
 * @Date 2022/11/11 20:25
 * @Version 1.0
 */
@Mapper
public interface CouponInfoMapper extends BaseMapper<CouponUsageInformation> {

    void addCoupons(@Param("couponUsageInformation") CouponUsageInformation couponUsageInformation);

    void editCoupons(CouponUsageInformation couponUsageInformation);

    List<CouponUsageInformation> queryAllById(String id);

    List<CouponManagementAll> queryUseCoupon(String Id);
}
