package com.gxa.modules.sys.controller.backStage.user.userManagement;

import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.backStage.user.Address;
import com.gxa.modules.sys.entity.backStage.user.DrugUserInformation;
import com.gxa.modules.sys.entity.backStage.user.UserManagement;
import com.gxa.modules.sys.service.user.UserManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.simpleframework.xml.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author :林溪
 * @date : 2022/11/11 9:17
 */
@Api(tags = "用户管理")
@RequestMapping("/userManagement")
@RestController
public class UserController {
    //用户基本信息
    static List<UserManagement>list = new ArrayList<>();
    static List<DrugUserInformation>drugUserInfo = new ArrayList<>();
    static List<Address>address = new ArrayList<>();

static{
    list.add(new UserManagement("1","张三","http://baidu.com","123456789",1,"2021-12-11 21:23:00"));
    list.add(new UserManagement("2","李四","http://baidu.com","123456789",1,"2021-12-12 21:23:00"));
    list.add(new UserManagement("3","王五","http://baidu.com","123456789",1,"2021-12-13 21:23:00"));
    list.add(new UserManagement("4","赵六","http://baidu.com","123456789",1,"2021-12-14 21:23:00"));
    list.add(new UserManagement("5","钱七","http://baidu.com","123456789",1,"2021-12-15 21:23:00"));
    list.add(new UserManagement("6","言八","http://baidu.com","123456789",1,"2021-12-16 21:23:00"));
    drugUserInfo.add(new DrugUserInformation("张三","好兄弟","12345679","2000-06-09","男","111111111","原发性高血压","http://baidu.com"));
    drugUserInfo.add(new DrugUserInformation("李四","兄弟","12345679","2000-06-10","男","111111111","原发性高血压","http://baidu.com"));
    drugUserInfo.add(new DrugUserInformation("王五","mam","12345679","2000-06-11","男","111111111","原发性高血压","http://baidu.com"));
    drugUserInfo.add(new DrugUserInformation("赵六","dad","12345679","2000-06-15","男","111111111","原发性高血压","http://baidu.com"));
    address.add(new Address("张三","132123123","四川省成都市高新区",1));
    address.add(new Address("李四","132123123","四川省成都市高新区",0));
    address.add(new Address("王五","132123123","四川省成都市高新区",0));
    address.add(new Address("赵六","132123123","四川省成都市高新区",0));
}



    @Autowired
    private UserManagementService userManagementService;

    @ApiOperation("筛选/首页列表")
    @GetMapping("/search")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",dataType = "String"),
            @ApiImplicitParam(name = "addTime",value = "注册时间",dataType = "String"),
            @ApiImplicitParam(name = "page",value = "当前页",dataType = "Integer")
    })
    public Result<List<UserManagement>> search(@RequestParam @ApiIgnore Map<String,Object> map){
    System.out.println(map);
        return new Result<List<UserManagement>>().ok(list);
    }

    @ApiOperation("批量/删除")
    @DeleteMapping("/deleteByIds")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",dataType = "String")
    })
    public Result deleteByIds(@RequestBody @ApiIgnore List<String> ids){
        return new Result().ok("删除成功");
    }

    @ApiOperation("查看用药人")
    @GetMapping("/queryDrugUserInformation/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户id",dataType = "String")
    })
    public Result<List<DrugUserInformation>> queryDrugUserInformation(@PathVariable("id")@ApiIgnore String id){
        System.out.println(id+"id");
        return new Result<List<DrugUserInformation>>().ok(drugUserInfo);
    }

    @ApiOperation("查看收货地址")
    @GetMapping("/queryAddress/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户id",dataType = "String")
    })
    public Result<List<Address>> queryAddress(@PathVariable("id")@ApiIgnore String id){
        System.out.println(id+"id");
        return new Result<List<Address>>().ok(address);
    }
}
