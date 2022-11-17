package com.gxa.modules.sys.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.modules.sys.entity.backStage.user.DrugUserInformation;
import com.gxa.modules.sys.mapper.backStage.user.DrugUserInformationMapper;
import com.gxa.modules.sys.service.user.DrugUserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :林溪
 * @date : 2022/11/12 15:33
 */
@Service
public class DrugUserInformationServiceImpl extends ServiceImpl<DrugUserInformationMapper, DrugUserInformation> implements DrugUserInformationService {
    @Autowired
    private DrugUserInformationMapper drugUserInformationMapper;

    //根据用户id查询
    @Override
    public List<DrugUserInformation> queryDrugUserInformation(String id) {
        List<DrugUserInformation> t_user_id = drugUserInformationMapper.queryDrugUserInformation(id);//selectList(new QueryWrapper<DrugUserInformation>().eq(true, "user_id", id));
        return t_user_id;
    }
}
