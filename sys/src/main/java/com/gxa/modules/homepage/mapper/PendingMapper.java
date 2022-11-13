package com.gxa.modules.homepage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.homepage.entity.Pending;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PendingMapper extends BaseMapper<Pending> {
    Integer queryPendingWaitOrder();
    Integer queryPendingWaitRefund();
    Integer queryPendingWaitToExamine();
    Integer queryPendingWaitConfirmReceipt();
    Integer queryPendingInventoryAlert();

}
