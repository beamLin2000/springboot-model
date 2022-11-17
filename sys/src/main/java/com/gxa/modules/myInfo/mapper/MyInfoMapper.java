package com.gxa.modules.myInfo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.myInfo.entity.MyInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyInfoMapper extends BaseMapper<MyInfo> {
    Integer querywaitPay(Integer id);
    Integer querywaitOrder(Integer id);
    Integer querywaitConfirmReceipt(Integer id);
    Integer querycompletedOrder(Integer id);
    Integer querycanceledOrder(Integer id);
    Integer queryrefund(Integer id);
}
