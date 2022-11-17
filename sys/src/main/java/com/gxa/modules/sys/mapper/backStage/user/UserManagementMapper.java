package com.gxa.modules.sys.mapper.backStage.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.sys.entity.backStage.user.UserManagement;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author :林溪
 * @date : 2022/11/12 15:27
 */
@Mapper
public interface UserManagementMapper extends BaseMapper<UserManagement> {
    //筛选
    List<UserManagement> search(@Param("username")String username,
                                @Param("createTime")String time,
                                @Param("page")Integer page,
                                @Param("limit")Integer limit);
    //根据id与版本号查询单个
    UserManagement queryById(@Param("id")String id,
                             @Param("version")Integer version);
}
