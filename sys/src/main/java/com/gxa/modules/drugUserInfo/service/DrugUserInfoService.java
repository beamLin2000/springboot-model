package com.gxa.modules.drugUserInfo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.modules.drugUserInfo.entity.DrugUserInfo;
import com.gxa.modules.drugUserInfo.entity.DrugUserInfoForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DrugUserInfoService extends IService<DrugUserInfo> {
    List<DrugUserInfo>drugUserInfoList(Integer userId);
    int addDrugUserInfo(DrugUserInfo drugUserInfo);
    int updateDrugUserInfo(DrugUserInfo drugUserInfo);
    DrugUserInfo selectById(Integer id);
    int id();
    int addDrugUserInfoForm(DrugUserInfoForm drugUserInfoForm);

    List<DrugUserInfo> selectDrugUserInfoP(Integer userId);

}
