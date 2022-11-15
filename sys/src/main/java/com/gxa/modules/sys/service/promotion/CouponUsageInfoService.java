package com.gxa.modules.sys.service.promotion;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.goods.goodsEntity.Drug;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponManagement;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponManagementAll;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponUsageInformation;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author LXD
 * @Date 2022/11/12 9:40
 * @Version 1.0
 */
public interface CouponUsageInfoService extends IService<CouponUsageInformation> {
    PageUtils searchCouponUsageInfo(Map<String, Object> params);

    void addCoupons(@Param("couponUsageInformation") CouponUsageInformation couponUsageInformation);

    void editCoupons(CouponUsageInformation couponUsageInformation);

    List<CouponUsageInformation> queryAllById(String id);

    List<CouponManagementAll> queryUseCoupon(List<Drug> drugs, String userId);
}
