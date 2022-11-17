package com.gxa.modules.myInfo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.myInfo.entity.MyInfo;
import com.gxa.modules.myInfo.entity.RefundInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CancelMapper extends BaseMapper<MyInfo> {
    boolean deleteByOrder(String orderNo);
    void updatePrescription(@Param("orderNo") String orderNo,@Param("status") Integer status);
    RefundInfo refunfInfo(String orderNo);
}
