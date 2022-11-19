package com.gxa.modules.drugUserInfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.modules.drugUserInfo.entity.DrugUserInfo;
import com.gxa.modules.drugUserInfo.entity.DrugUserInfoForm;
import com.gxa.modules.drugUserInfo.mapper.DrugUserInfoMapper;
import com.gxa.modules.drugUserInfo.service.DrugUserInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DrugUserInfoServiceImpl extends ServiceImpl<DrugUserInfoMapper, DrugUserInfo> implements DrugUserInfoService {

    @Autowired
    private DrugUserInfoMapper drugUserInfoMapper;

    @Override
    public List<DrugUserInfo> drugUserInfoList(Integer userId) {
        List<DrugUserInfo> drugUserInfo = this.baseMapper.selectList(new QueryWrapper<DrugUserInfo>().eq("user_id", userId));
        return drugUserInfo;
    }




    @Override
    public int addDrugUserInfo(DrugUserInfo drugUserInfo) {

        int id = this.drugUserInfoMapper.id() + 1;
        int i = this.baseMapper.insert(drugUserInfo);
        if (drugUserInfo.getId() == id){


            return i;
        }
        return i;

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateDrugUserInfo(DrugUserInfo drugUserInfo) {
//        UpdateWrapper<DrugUserInfo> wrapper = new UpdateWrapper<>();
//        wrapper.eq("id",drugUserInfo.getId()).eq("version",drugUserInfo.getVersion());
//        int i = this.baseMapper.update(drugUserInfo,wrapper);
        int i = this.drugUserInfoMapper.updateDrug(drugUserInfo);

        if (i != 1){
//            throw new RuntimeException("插入失败");
            return i;

        }
        drugUserInfo.setVersion(drugUserInfo.getVersion()+1);
        this.drugUserInfoMapper.update(drugUserInfo);
        return i;
    }

    @Override
    public DrugUserInfo selectById(Integer id) {
        DrugUserInfo drugUserInfo = this.baseMapper.selectOne(new QueryWrapper<DrugUserInfo>().eq("id", id));
        return drugUserInfo;
    }

    @Override
    public int id() {
        int id = this.drugUserInfoMapper.id() + 1;
        return id;
    }

    @Override
    public int addDrugUserInfoForm(DrugUserInfoForm drugUserInfoForm) {
        int id = this.drugUserInfoMapper.id() + 1;
        int i = this.drugUserInfoMapper.addDrugUserInfoForm(drugUserInfoForm);
        if (drugUserInfoForm.getId() == id){


            return i;
        }
        return i;

    }


    @Override
    public List<DrugUserInfo> selectDrugUserInfoP(Integer userId) {
        List<DrugUserInfo> drugUserInfos = this.drugUserInfoMapper.selectDrugUserInfoP(userId);
        return drugUserInfos;
    }


}
