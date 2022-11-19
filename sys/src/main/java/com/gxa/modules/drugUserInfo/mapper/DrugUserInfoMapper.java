package com.gxa.modules.drugUserInfo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.drugUserInfo.entity.DrugUserInfo;
import com.gxa.modules.drugUserInfo.entity.DrugUserInfoForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface DrugUserInfoMapper extends BaseMapper<DrugUserInfo> {
    void update(@Param("drugUserInfo") DrugUserInfo drugUserInfo);
    DrugUserInfo queryIdAndVersion(Integer id);
    int updateDrug(@Param("drugUserInfo") DrugUserInfo drugUserInfo);
    int id();
    int addDrugUserInfoForm(@Param("drugUserInfo") DrugUserInfoForm drugUserInfo);


    List<DrugUserInfo> selectDrugUserInfoP(Integer userId);

}
