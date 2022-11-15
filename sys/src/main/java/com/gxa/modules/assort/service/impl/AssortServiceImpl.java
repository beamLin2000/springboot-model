package com.gxa.modules.assort.service.impl;

import com.gxa.modules.assort.dto.DrugDto;
import com.gxa.modules.assort.mapper.AssortMapper;
import com.gxa.modules.assort.service.AssortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssortServiceImpl implements AssortService {

    @Autowired
    private AssortMapper assortMapper;

    @Override
    public List<DrugDto> queryAllByDrugType(String drugType) {
        List<DrugDto> drugDtos = this.assortMapper.queryAllDrugByDrugType(drugType);
        return drugDtos;
    }

    @Override
    public List<DrugDto> queryDrugByCondition(String drugType, String condition,String sort) {
        List<DrugDto> drugDtos = this.assortMapper.queryDrugByCondition(drugType,condition,sort);
        return drugDtos;
    }
}
