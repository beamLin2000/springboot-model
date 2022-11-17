package com.gxa.modules.assort.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.assort.dto.DrugDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface AssortMapper extends BaseMapper {
    List<DrugDto> queryAllDrugByDrugType(String drugType);
    List<DrugDto> queryDrugByCondition(@Param("drugType") String drugType, @Param("condition") String condition,@Param("sort") String sort);
}
