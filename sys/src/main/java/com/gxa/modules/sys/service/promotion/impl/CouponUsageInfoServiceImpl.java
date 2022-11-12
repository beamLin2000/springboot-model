package com.gxa.modules.sys.service.promotion.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Query;
import com.gxa.modules.sys.entity.backStage.promotion.couponManagement.CouponUsageInformation;
import com.gxa.modules.sys.mapper.backStage.promotion.couponManagement.CouponInfoMapper;
import com.gxa.modules.sys.service.promotion.CouponManagementService;
import com.gxa.modules.sys.service.promotion.CouponUsageInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

}
