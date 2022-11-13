package com.gxa.modules.goods.goodsServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Query;
import com.gxa.modules.goods.goodsEntity.Medicinal;
import com.gxa.modules.goods.goodsMapper.MedicinalMapper;
import com.gxa.modules.goods.goodsService.MedicinalService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MedicinalServiceImpl extends ServiceImpl<MedicinalMapper, Medicinal> implements MedicinalService {

    /**
     * 查询药品一级分类的所有数据
     * @param params 分页参数
     * @return
     */
    @Override
    public PageUtils list(Map<String, Object> params) {
        IPage<Medicinal> page = this.page(new Query<Medicinal>().getPage(params),
                new QueryWrapper<Medicinal>().eq("`rank`","一级"));

        return new PageUtils(page);
    }

    @Override
    public PageUtils listTwo(Map<String, Object> params) {
        IPage<Medicinal> page = this.page(new Query<Medicinal>().getPage(params),
                new QueryWrapper<Medicinal>().eq("`rank`","二级"));

        return new PageUtils(page);
    }
}
