package com.gxa.modules.sys.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.sys.entity.backStage.user.UserManagement;
import com.gxa.modules.sys.mapper.backStage.user.AddressMapper;
import com.gxa.modules.sys.mapper.backStage.user.DrugUserInformationMapper;
import com.gxa.modules.sys.mapper.backStage.user.UserManagementMapper;
import com.gxa.modules.sys.service.user.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author :林溪
 * @date : 2022/11/12 15:23
 */
@Service
public class UserManagementServiceImpl extends ServiceImpl<UserManagementMapper, UserManagement> implements UserManagementService {

    @Autowired
    private UserManagementMapper userManagementMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private DrugUserInformationMapper drugUserInformationMapper;

    @Override
    public PageUtils search(Map<String, Object> map) {
        String username = (String)map.get("username");
        String createTime = (String)map.get("createTime");
        Integer page = (Integer.parseInt(String.valueOf(map.get("page")))-1)*10;
        Integer limit = Integer.parseInt((String) map.get("limit"));
//        IPage page = page(new Query<UserManagement>().getPage(map),
//                new QueryWrapper<UserManagement>().like(StringUtils.isNotEmpty(username),"user_name",username)
//                        .le(StringUtils.isNotEmpty(addTime),"create_time",addTime)
//        .orderByDesc("id"));
        List<UserManagement> search = userManagementMapper.search(username, createTime, page,limit);
        Integer pageSizeInteger = search.size()/10;
        Double pageSizeDouble = search.size()/(double)page;
        return new PageUtils(search,search.size(),pageSizeDouble>pageSizeInteger?pageSizeInteger+1:pageSizeInteger,page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteByIds(List<String> ids) throws Exception {
        int i = userManagementMapper.deleteBatchIds(ids);
        for(int j = 0; j < ids.size();j++){//若一个账号发生了删除,则其下的所有的关联地址与用药人信息应当被删除
            HashMap<String,Object> stringIntegerHashMap = new HashMap<>();
            stringIntegerHashMap.put("t_user_id",ids.get(i));
            //删除对应的地址
            int i1 = addressMapper.deleteByMap(stringIntegerHashMap);
            //删除对应的用药人信息
            int i2 = drugUserInformationMapper.deleteByMap(stringIntegerHashMap);
            if(i1==-1||i2==-1){
                throw new Exception("用户删除失败");
            }
        }
        if(i==-1){
            throw new Exception("用户删除失败");
        }
        return i;
    }

    @Override
    public Integer updateStatus(String id, Integer status,Integer version) {
        Integer integer = addressMapper.updateStatus(id, status,version);
        return integer;
    }

    @Override
    public UserManagement queryById(String id, Integer version) {
        return userManagementMapper.queryById(id,version);
    }

}
