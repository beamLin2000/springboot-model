package com.gxa.modules.sys.mapper.backStage.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.sys.entity.backStage.user.DrugUserInformation;
import com.gxa.modules.sys.entity.backStage.user.UserManagement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author :林溪
 * @date : 2022/11/12 15:35
 */
@Mapper
public interface DrugUserInformationMapper extends BaseMapper<DrugUserInformation> {

    //查看用药人信息
    List<DrugUserInformation> queryDrugUserInformation(String id);

}
