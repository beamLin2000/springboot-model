package com.gxa.modules.login.redis;

import com.gxa.common.utils.Base64Utils;
import com.gxa.common.utils.JsonUtils;
import com.gxa.common.utils.RedisKeys;
import com.gxa.common.utils.RedisUtils;
import com.gxa.modules.assort.dto.DrugDto;
import com.gxa.modules.login.entity.SysUser;
import com.gxa.modules.login.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class SysUserRedis {

    private RedisUtils redisUtils;

    public void addToken(String token, User user){

        //token --- user
        //1111--user
        /**
         * sys:user:username:1111  ---user
         * sys:user:username:2222 ----user
         * sys:user:username:3333 ----user
         */
        this.redisUtils.set(RedisKeys.getSysUserTokenKey(user.getUsername(),token),user);
    }

    public User getUserByToken(String token){
        String userJsonStr = this.redisUtils.get(RedisKeys.getSysUserTokenKey(token));
        User user = JsonUtils.parseObject(userJsonStr, User.class);

        return user;
    }
    public void addSysToken(String token, SysUser sysUser){

        //token --- user
        //1111--user
        /**
         * sys:user:username:1111  ---user
         * sys:user:username:2222 ----user
         * sys:user:username:3333 ----user
         */
        this.redisUtils.set(RedisKeys.getSysUserTokenKey(sysUser.getUsername(),token),sysUser);
    }

    public SysUser getSysUserByToken(String token){
        String userJsonStr = this.redisUtils.get(RedisKeys.getSysUserTokenKey(token));
        SysUser sysUser = JsonUtils.parseObject(userJsonStr, SysUser.class);

        return sysUser;
    }


    public void addAssortDrug(String drugType, List<DrugDto> drugDtos){
        List<String> list = new ArrayList<>();
        for (DrugDto drugDto:drugDtos){
            String drugStr = JsonUtils.toJsonString(drugDto);
            list.add(drugStr);
        }

        this.redisUtils.set(RedisKeys.getAssortDrugKey(drugType),list);
    }

    public void addConditionAssortDrug(String drugType,String condition,String sort, List<DrugDto> drugDtos){
        List<String> list = new ArrayList<>();
        for (DrugDto drugDto:drugDtos){
            String drugStr = JsonUtils.toJsonString(drugDto);
            list.add(drugStr);
        }

        this.redisUtils.set(RedisKeys.getAssortConditionDrugKey(drugType,condition,sort),list);
    }


    public List<DrugDto> getDrugList(String key,Integer start,Integer end){
        List<Object> list = this.redisUtils.getList(RedisKeys.getAssortDrugKey(key), start, end);
        List<DrugDto> drugDtos = new ArrayList<>();
        for (Object o :
                list) {
            String s = o.toString();
            DrugDto drugDto = JsonUtils.parseObject(s,DrugDto.class);
            drugDtos.add(drugDto);
        }
        return drugDtos;

    }
    public List<DrugDto> getDrugListCondition(String key,String condition,String sort,Integer start,Integer end){
        List<Object> list = this.redisUtils.getList(RedisKeys.getAssortConditionDrugKey(key,condition,sort), start, end);
        List<DrugDto> drugDtos = new ArrayList<>();
        for (Object o :
                list) {
            String s = o.toString();
            DrugDto drugDto = JsonUtils.parseObject(s,DrugDto.class);
            drugDtos.add(drugDto);
        }
        return drugDtos;

    }

    public void deleteDrugList(String key){
        this.redisUtils.delete(RedisKeys.getAssortDrugKey(key));
    }
}
