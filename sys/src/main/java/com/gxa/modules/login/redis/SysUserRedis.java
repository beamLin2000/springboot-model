package com.gxa.modules.login.redis;

import com.gxa.common.utils.JsonUtils;
import com.gxa.common.utils.RedisKeys;
import com.gxa.common.utils.RedisUtils;
import com.gxa.modules.assort.dto.DrugDto;
import com.gxa.modules.fristpage.entity.Goods;
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
        this.redisUtils.set(RedisKeys.getSysUserTokenKey(user.getPhoneNumber().toString(),token),user);
    }
    public void addToken(String captcha, String phone){
        this.redisUtils.set(captcha,phone,240000);
    }

    public User getUserByToken(String token){
        System.out.println("sssss" + token);
        String userJsonStr = this.redisUtils.get(RedisKeys.getSysUserTokenKey(token));
        System.out.println(userJsonStr);
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
    public void addUserToken(String token, User user){

        //token --- user
        //1111--user
        /**
         * sys:user:username:1111  ---user
         * sys:user:username:2222 ----user
         * sys:user:username:3333 ----user
         */
        this.redisUtils.set(RedisKeys.getUserTokenKey(token),user);
    }

    public SysUser getSysUserByToken(String token){
        String userJsonStr = this.redisUtils.get(RedisKeys.getSysUserTokenKey(token));
        SysUser sysUser = JsonUtils.parseObject(userJsonStr, SysUser.class);

        return sysUser;
    }
    public String getCaptcha(String phoneNum){
        String userJsonStr = this.redisUtils.get(phoneNum);

        return userJsonStr;
    }
    public void addAssortDrug(String drugType, List<DrugDto> drugDtos){
        List<String> list = new ArrayList<>();
        for (DrugDto drugDto:drugDtos){
            String drugStr = JsonUtils.toJsonString(drugDto);
            list.add(drugStr);
        }


        this.redisUtils.set(RedisKeys.getAssortDrugKey(drugType),list);
    }
    public void addAssortGoods(String drugType, List<Goods> goods){
        List<String> list = new ArrayList<>();
        for (Goods good:goods){
            String drugStr = JsonUtils.toJsonString(goods);
            list.add(drugStr);
        }


        this.redisUtils.set(RedisKeys.getAssortDrugKey(drugType),list);
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
    public void deleteDrugList(String key){
        this.redisUtils.delete(RedisKeys.getAssortDrugKey(key));
    }
}
