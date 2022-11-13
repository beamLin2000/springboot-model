package com.gxa.modules.sys.service.Impl.goods;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Query;
import com.gxa.modules.sys.entity.User;
import com.gxa.modules.sys.entity.goods.Medicinal;
import com.gxa.modules.sys.mapper.UserMapper;
import com.gxa.modules.sys.mapper.goods.MedicinalMapper;
import com.gxa.modules.sys.service.goods.MedicinalService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MedicinalServiceImpl extends ServiceImpl<MedicinalMapper, Medicinal> implements MedicinalService {

    /**
     * 查询药品一级分类的所有数据
     * @param params 分页参数
     * @return
     */
    @Override
    public PageUtils List(Map<String, Object> params) {
        IPage<Medicinal> page = this.page(new Query<Medicinal>().getPage(params),
                new QueryWrapper<Medicinal>().eq("`rank`","一级"));

        return new PageUtils(page);
    }
}
