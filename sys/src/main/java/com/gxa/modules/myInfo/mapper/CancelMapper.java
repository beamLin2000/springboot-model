package com.gxa.modules.myInfo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.myInfo.entity.MyInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CancelMapper extends BaseMapper<MyInfo> {
    boolean deleteByOrder(String orderNo);
}
