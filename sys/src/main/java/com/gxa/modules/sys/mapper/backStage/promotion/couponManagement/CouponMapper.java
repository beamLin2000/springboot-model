package com.gxa.modules.sys.mapper.backStage.promotion.couponManagement;

import com.alipay.api.domain.Coupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponAddClass;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponAddDrug;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponManagement;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponManagementAll;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author LXD
 * @Date 2022/11/11 15:43
 * @Version 1.0
 */
@Mapper
public interface CouponMapper extends BaseMapper<CouponManagement> {
    CouponManagementAll queryById(@Param("id") String id);

    void add(CouponManagementAll couponManagementAll);

    void couponAddDrug(List<CouponAddDrug> couponAddDrugs);

    void couponAddClass(List<CouponAddClass> couponAddClasses);
}
