package com.gxa.modules.sys.controller.backStage.user.userManagement;

import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.backStage.user.Address;
import com.gxa.modules.sys.entity.backStage.user.DrugUserInformation;
import com.gxa.modules.sys.service.user.AddressService;
import com.gxa.modules.sys.service.user.UserManagementService;
import com.gxa.modules.sys.service.user.impl.DrugUserInformationServiceImpl;
import io.swagger.annotations.*;
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
            @ApiImplicitParam(paramType = "query",name = "username",value = "用户名",dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "createTime",value = "注册时间",dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "page",value = "当前页",dataType = "Integer")
    })
    public Result<PageUtils> search(@RequestParam @ApiIgnore Map<String,Object> map){
        System.out.println(map);
        PageUtils search = userManagementService.search(map);
        return new Result<PageUtils>().ok(search);
    }

    @ApiOperation("批量/删除")
    @DeleteMapping("/deleteByIds")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body",name = "username",value = "用户名",dataType = "String")
    })
    public Result deleteByIds(@RequestBody @ApiIgnore List<String> ids)throws Exception{
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
    public Result<List<Address>> queryAddress(@PathVariable("id")@ApiIgnore String id){
        System.out.println(id+"id");
        List<Address> addresses = addressService.queryAddress(id);
        return new Result<List<Address>>().ok(addresses);
    }

    //账户启用状态
    @ApiOperation("账户启用状态")
    @PutMapping("/updateStatus/{id}/{status}/{version}")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "id",value = "用户id",dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "status",value = "用户当前id",dataType = "Integer")
    })
    public Result updateStatus(@PathVariable("id")Integer id,
                               @PathVariable("status")Integer status,
                               @PathVariable("version")Integer version){
        Integer integer = userManagementService.updateStatus(id, status == 1 ? 0 : 1,version);
        if(integer!=-1){
            return new Result().ok("修改成功");
        }
        return new Result().error("修改失败");
    }
}
