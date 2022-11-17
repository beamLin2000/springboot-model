package com.gxa.modules.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.order.dto.ExpressDto;
import com.gxa.modules.order.entity.ExpressDetail;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface ExpressMapper extends BaseMapper<ExpressDto> {
    void insertExpressDetail(ExpressDetail expressDetail);
    ExpressDetail selectExpressDetailByOrderNo(String orderNo);
}
