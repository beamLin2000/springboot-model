package com.gxa.modules.sys.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.modules.sys.entity.backStage.user.DrugUserInformation;
import com.gxa.modules.sys.mapper.backStage.user.DrugUserInformationMapper;
import com.gxa.modules.sys.service.user.DrugUserInformationService;
import org.springframework.stereotype.Service;

/**
 * @author :林溪
 * @date : 2022/11/12 15:33
 */
@Service
public class DrugUserInformationServiceImpl extends ServiceImpl<DrugUserInformationMapper, DrugUserInformation> implements DrugUserInformationService {
}
