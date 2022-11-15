package com.gxa.modules.assort.service;

import com.gxa.modules.assort.dto.DrugDto;

import java.util.List;

public interface AssortService {
    List<DrugDto> queryAllByDrugType(String drugType);
}
