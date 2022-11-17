package com.gxa.modules.login.controller;

import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.gxa.common.utils.AssertUtils;
import com.gxa.common.utils.ErrorCode;
import com.gxa.common.utils.Result;
import com.gxa.common.validator.ValidatorUtils;
import com.gxa.common.validator.group.AddGroup;
import com.gxa.modules.login.dto.Member;
import com.gxa.modules.login.dto.MemberMannage;
import com.gxa.modules.login.dto.Role;
import com.gxa.modules.login.service.MemberService;
import com.gxa.modules.login.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.roles;

@RestController
@Api(tags = "权限接口")
@RequestMapping("/Jurisdiction")
public class JurisdictionController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private RoleService roleService;

    @ApiOperation(value="成员管理查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "pageNum",value ="当前是第几页",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "pageSize",value ="每页显示多少条",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "username",value ="名字查询",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "role",value ="角色查询",dataType ="String")

    }
    )
    @GetMapping("/memberList")
    public Result queryMemberList(@RequestParam @ApiIgnore Map<String,Object> params){
        Result result = this.memberService.queryAll(params);
        return result;
    }
    @ApiOperation("成员管理删除")
    @DeleteMapping("/delMember/{username}")
    @ApiImplicitParam(paramType = "query",name = "username",value ="用户名",dataType ="List",required = true)
    public Result delete(@RequestParam("username") List<String> username){
        AssertUtils.isListEmpty(username, "用户名不为能空");
        for(String name : username){
            this.memberService.delete(name);
        }
        return new Result<>().ok();
    }
    @GetMapping("/toMemberAdd")
    @ApiOperation("成员管理添加获取8位随机密码及角色列表")
    public Result list01(){
        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        List<String> roleNames = this.roleService.roleName();
        Map map = new HashMap();
        map.put("pwd",idd[0]);
        map.put("roleName",roleNames);
        return new Result<>().ok(map);
    }
    @ApiOperation("成员管理添加")
    @PostMapping("/addMember")
    public Result add(@RequestBody Member member){
        ValidatorUtils.validateEntity(member, AddGroup.class);
        String uuid = String.valueOf(UUID.randomUUID());
        member.setSalt(uuid);
        String password = new SimpleHash("MD5", member.getPassword(), uuid, 1024).toString();
        member.setPassword(password);
        Date date = new Date();
        long time = date.getTime();
        date.setTime(time);
        member.setCreateTime(date);
        this.memberService.add(member);
        return new Result<>().ok();
    }
    @GetMapping("/toupdateMember")
    @ApiOperation("成员管理编辑回显")
    @ApiImplicitParam(paramType = "query",name = "username",value ="用户名",dataType ="String",required = true)
    public Result toupdateMember(@RequestParam String username){
        AssertUtils.isBlank(username,"用户名不能为空");
        MemberMannage memberMannage = memberService.queryByName(username);
        memberMannage.setSalt("");
        Map map = new HashMap();
        map.put("member",memberMannage);
        return new Result<>().ok(map);
    }
    @PutMapping("/updateMember")
    @ApiOperation("成员管理编辑")
    public Result updateMember(@RequestBody MemberMannage member){
        AssertUtils.isNull(member, "不为能空");
        memberService.updateMember(member);
        return new Result<>().ok();
    }
    @ApiOperation("修改成员启用状态")
    @GetMapping("/updateMember")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "status",value ="状态",dataType ="int",required = true),
            @ApiImplicitParam(paramType = "query",name = "username",value ="用户名",dataType ="String",required = true)

    }
    )
    public Result updateMember(@RequestParam @ApiIgnore Map<String,Object> params){
        AssertUtils.isNull(params, "状态或用户名不为能空");
        this.memberService.updateStatus(params);
        return new Result<>().ok();
    }
    @ApiOperation(value="角色管理查询")
    @GetMapping("/roleList")
    @ApiImplicitParam(paramType = "query",name = "name",value ="角色名",dataType ="String")
    public Result queryRoleList(@RequestParam @ApiIgnore Map<String,Object> params){
        Map map = new HashMap();
        List<Role> roles = this.roleService.queryAll(params);
        map.put("roles",roles);
        return new Result<>().ok(map);
    }
    @ApiOperation("角色管理删除")
    @DeleteMapping("/delrole/{name}")
    @ApiImplicitParam(paramType = "query",name = "name",value ="角色名",dataType ="String",required = true)
    public Result delete(@RequestParam("name") String name){
        AssertUtils.isBlank(name, "角色名不为能空");
        if (name.equals("管理员")){
            return new Result().error(ErrorCode.FORBIDDEN,"禁止删除");
        }
        this.roleService.del(name);
        return new Result<>().ok();
    }
    @ApiOperation("修改角色启用状态")
    @GetMapping("/updateRole")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "status",value ="状态",dataType ="int",required = true),
            @ApiImplicitParam(paramType = "query",name = "name",value ="角色名",dataType ="String",required = true)

    }
    )
    public Result updateRole(@RequestParam @ApiIgnore Map<String,Object> params){
        AssertUtils.isNull(params, "状态或角色名不为能空");
        if (params.get("name").toString().equals("管理员")){
            return new Result().error(ErrorCode.FORBIDDEN,"禁止修改");
        }
        this.roleService.updateStatus(params);
        return new Result<>().ok();
    }
}
