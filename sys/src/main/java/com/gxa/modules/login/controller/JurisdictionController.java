package com.gxa.modules.login.controller;

import com.gxa.common.utils.AssertUtils;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Result;
import com.gxa.common.validator.ValidatorUtils;
import com.gxa.common.validator.group.AddGroup;
import com.gxa.modules.login.dto.Member;
import com.gxa.modules.login.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@Api(tags = "权限接口")
@RequestMapping("/Jurisdiction")
public class JurisdictionController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value="成员管理查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前是第几页",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "limit",value ="每页显示多少条",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "username",value ="名字查询",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "role",value ="角色查询",dataType ="String")

    }
    )
    @GetMapping("/list")
    public Result queryList(@RequestParam @ApiIgnore Map<String,Object> params){
        PageUtils pageUtils = this.memberService.queryAll(params);
        Result result = this.memberService.queryA(params);
        return result;
    }
    @ApiOperation("成员删除")
    @DeleteMapping("/del/{username}")
    @ApiImplicitParam(paramType = "query",name = "username",value ="用户名",dataType ="List",required = true)
    public Result delete(@RequestParam("username") List<String> username){
        AssertUtils.isListEmpty(username, "用户名不为能空");
        for(String name : username){
            this.memberService.delete(name);
        }
        return new Result<>().ok();
    }
    @GetMapping("/toAdd")
    @ApiOperation("获取随机密码")
    public Result list01(){
        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        Map map = new HashMap();
        map.put("pwd",idd[0]);
        return new Result<>().ok(map);
    }
    @ApiOperation("添加")
    @PostMapping("/add")
    public Result add(@RequestBody Member member){

        ValidatorUtils.validateEntity(member, AddGroup.class);
        this.memberService.save(member);
        return new Result<>().ok();
    }
}
