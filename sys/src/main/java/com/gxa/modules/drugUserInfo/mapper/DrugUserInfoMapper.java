package com.gxa.modules.drugUserInfo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.drugUserInfo.entity.DrugUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface DrugUserInfoMapper extends BaseMapper<DrugUserInfo> {
    void update(@Param("drugUserInfo") DrugUserInfo drugUserInfo);
    DrugUserInfo queryIdAndVersion(Integer id);
    int updateDrug(@Param("drugUserInfo") DrugUserInfo drugUserInfo);

}
