package com.gxa.modules.assort.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gxa.common.utils.Base64Utils;
import com.gxa.common.utils.RedisKeys;
import com.gxa.common.utils.Result;
import com.gxa.modules.assort.dto.DrugDto;
import com.gxa.modules.assort.service.AssortService;

import com.gxa.modules.login.redis.SysUserRedis;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "分类查询")
@RestController
@RequestMapping("/assort")
public class AssortController {

    @Autowired
    private AssortService assortService;

    @Autowired
    private SysUserRedis sysUserRedis;

    @ApiOperation(value="药品分类查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "start",value ="数据从第几条开始",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "end",value ="数据从第几条结束",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "drugType",value ="药品类型",dataType ="String"),
    }
    )
    @PostMapping("/drugAssortList")
    public Result drugAssortList(@RequestParam @ApiIgnore Map<String,Object> params){


        Map map = new HashMap();

        Integer start = Integer.parseInt(params.get("start").toString());
        Integer end = Integer.parseInt(params.get("end").toString());

        String drugType = params.get("drugType").toString();

        List<DrugDto> drugDtos1 = this.sysUserRedis.getDrugList(drugType,start,end);
        map.put("drugDtos",drugDtos1);

        if (drugDtos1 == null){
            List<DrugDto> drugDtos = this.assortService.queryAllByDrugType(drugType);

            this.sysUserRedis.addAssortDrug(drugType,drugDtos);

            map.put("drugDtos",drugDtos);

        }


        return new Result().ok(map);
    }
}
