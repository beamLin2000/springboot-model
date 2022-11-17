package com.gxa.modules.sys.controller.backStage.user.userManagement;

import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.RedisUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.backStage.user.Address;
import com.gxa.modules.sys.entity.backStage.user.DrugUserInformation;
import com.gxa.modules.sys.entity.backStage.user.UserManagement;
import com.gxa.modules.sys.service.user.AddressService;
import com.gxa.modules.sys.service.user.UserManagementService;
import com.gxa.modules.sys.service.user.impl.DrugUserInformationServiceImpl;
import io.swagger.annotations.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * @author :林溪
 * @date : 2022/11/11 9:17
 */
@Api(tags = "后台:用户管理")
@RequestMapping("/userManagement")
@RestController
public class UserController {

    @Autowired
    private AddressService addressService;
    @Autowired
    private DrugUserInformationServiceImpl drugUserInformationService;
    @Autowired
    private UserManagementService userManagementService;

    @ApiOperation("筛选/首页列表")
    @GetMapping("/search")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "userName",value = "用户名",dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "createTime",value = "注册时间",dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "page",value = "当前页",dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "limit",value = "每一页显示数据",dataType = "Integer")
    })
    public Result<PageUtils> search(@RequestParam @ApiIgnore Map<String,Object> map){
        System.out.println("map"+map);
        if(!StringUtils.isNotEmpty((String) map.get("page"))){
            return new Result<PageUtils>().error("page,当前页数不能为null");
        }
        if(!StringUtils.isNotEmpty((String) map.get("limit"))){
            return new Result<PageUtils>().error("page,每一页显示条数不能为null");
        }
        System.out.println(map);
        PageUtils search = userManagementService.search(map);
        return new Result<PageUtils>().ok(search);
    }

    @ApiOperation("批量/删除")
    @DeleteMapping("/deleteByIds")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body",name = "ids",value = "用户id",dataType = "Array")
    })
    public Result deleteByIds(@RequestBody @ApiIgnore List<String> ids)throws Exception{
        if(ids.size()==0){
            return new Result().error("被删除的数组为null,请选择数据后再提交");
        }
        Integer integer = userManagementService.deleteByIds(ids);
        if(integer!=-1){
            return new Result().ok("删除成功");
        }
        return new Result().ok("删除失败");
    }

    @ApiOperation("查看用药人")
    @GetMapping("/queryDrugUserInformation/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "ok",response = DrugUserInformation.class)
    })
    public Result<List<DrugUserInformation>> queryDrugUserInformation(@PathVariable("id")@ApiIgnore String id){
        if(!StringUtils.isNotEmpty(id)){
            return new Result<List<DrugUserInformation>>().error("传递的id不能为null");
        }
        System.out.println(id+"id");
        List<DrugUserInformation> drugUserInformations = drugUserInformationService.queryDrugUserInformation(id);
        return new Result<List<DrugUserInformation>>().ok(drugUserInformations);
    }

    @ApiOperation("查看收货地址")
    @GetMapping("/queryAddress/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "ok",response = Result.class),
            @ApiResponse(code = 200,message = "ok",response = Address.class)
    })
    public Result<List<Address>> queryAddress(@PathVariable(value = "id")@ApiIgnore String id){
        if(!StringUtils.isNotEmpty(id)){
            return new Result<List<Address>>().error("传递的id不能为null");
        }
        System.out.println(id+"id");
        List<Address> addresses = addressService.queryAddress(id);
        return new Result<List<Address>>().ok(addresses);
    }

    //账户启用状态
    @ApiOperation("账户启用状态")
    @PutMapping("/updateStatus/{id}/{status}/{version}")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "status",value = "用户当前状态",dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "version",value = "版本号",dataType = "Integer")
    })
    public Result updateStatus(@PathVariable("id")String id,
                               @PathVariable("status")Integer status,
                               @PathVariable("version")Integer version){
        UserManagement userManagement = userManagementService.queryById(id,version);
        if(userManagement==null){
            return new Result().error("该用户已被修改,请勿重复发送请求");
        }
        Integer integer = userManagementService.updateStatus(id, status == 1 ? 0 : 1,version);
        if(integer!=-1){
            return new Result().ok("修改成功");
        }
        return new Result().error("修改失败");
    }
}
