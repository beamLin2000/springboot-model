package com.gxa.modules.drugUserInfo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.modules.drugUserInfo.entity.DrugUserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DrugUserInfoService extends IService<DrugUserInfo> {
    List<DrugUserInfo>drugUserInfoList(Integer userId);
    void addDrugUserInfo(DrugUserInfo drugUserInfo);
    int updateDrugUserInfo(DrugUserInfo drugUserInfo);
    DrugUserInfo selectById(Integer id);

}
