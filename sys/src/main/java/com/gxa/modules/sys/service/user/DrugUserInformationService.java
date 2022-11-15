package com.gxa.modules.sys.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.modules.sys.entity.backStage.user.DrugUserInformation;

import java.util.List;

/**
 * @author :林溪
 * @date : 2022/11/12 15:33
 */
public interface DrugUserInformationService extends IService<DrugUserInformation> {
    //查看用药人
    List<DrugUserInformation> queryDrugUserInformation(String id);
}
