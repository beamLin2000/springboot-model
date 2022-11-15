package com.gxa.modules.goods.goodsService.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Query;
import com.gxa.modules.goods.goodsEntity.Drug;
import com.gxa.modules.goods.goodsMapper.DrugGoodsMapper;
import com.gxa.modules.goods.goodsService.DrugService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DrugServiceImpl extends ServiceImpl<DrugGoodsMapper, Drug> implements DrugService {
    @Override
    public PageUtils list(Map<String, Object> params) {
        IPage<Drug> page = this.page(new Query<Drug>().getPage(params),
                new QueryWrapper<Drug>().like(StringUtils.isNotEmpty((String) params.get("drugName")),"`drug_name`",params.get("drugName"))
                .eq(StringUtils.isNotEmpty((String) params.get("medicinalId")),"medicinal_id",params.get("medicinalId"))
                .eq(StringUtils.isNotEmpty((String) params.get("symptomId")),"symptom_id",params.get("symptomId"))
                .eq(StringUtils.isNotEmpty((String) params.get("shelves")),"`shelves`",params.get("shelves"))
                .eq(StringUtils.isNotEmpty((String) params.get("state")),"`state`",params.get("state"))
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils listCheck(Map<String, Object> params) {
        IPage<Drug> page = this.page(new Query<Drug>().getPage(params),
                new QueryWrapper<Drug>().like(StringUtils.isNotEmpty((String) params.get("drugName")),"`drug_name`",params.get("drugName"))
                        .eq(StringUtils.isNotEmpty((String) params.get("medicinalId")),"medicinal_id",params.get("medicinalId"))
                        .eq(StringUtils.isNotEmpty((String) params.get("symptomId")),"symptom_id",params.get("symptomId"))
                        .eq("state","待审核")
        );

        return new PageUtils(page);
    }
}
