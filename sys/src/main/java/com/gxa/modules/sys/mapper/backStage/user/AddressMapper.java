package com.gxa.modules.sys.mapper.backStage.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.sys.entity.backStage.user.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author :林溪
 * @date : 2022/11/12 15:35
 */
@Mapper
public interface AddressMapper extends BaseMapper<Address> {
    Integer updateStatus(@Param("id")String id,
                         @Param("status")Integer status,
                         @Param("version")Integer version);
}
