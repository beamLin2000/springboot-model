package com.gxa.modules.sys.controller.backStage.promotion.timeLimitedSecondKill;

import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.backStage.promotion.timeLimitedSecondKill.LimitedTimeFlashDeal;
import com.gxa.modules.sys.entity.goods.Drug;
import com.gxa.modules.sys.service.promotion.TimeLimitedSecondKillService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author :林溪
 * @date : 2022/11/11 14:11
 */
@RestController
@Api(tags = "限时秒杀")
@RequestMapping("/timeLimitedSecondKill")
public class TimeLimitedSecondKillController {

    @Autowired
    private TimeLimitedSecondKillService timeLimitedSecondKillService;

    @GetMapping("/search")
    @ApiOperation(value = "筛选")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "drugName",value ="药品名称",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前页",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "status",value ="状态",dataType ="String")
    })
    public Result<PageUtils> search(@RequestParam @ApiIgnore Map<String,Object> map){
        PageUtils pageUtils = timeLimitedSecondKillService.queryPage(map);
        return new Result<PageUtils>().ok(pageUtils);
    }

    @GetMapping("/queryById")
    @ApiOperation(value = "查看/编辑Pre")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "id",value ="药品id",dataType ="Integer")
    })
    public Result<LimitedTimeFlashDeal> queryById(@RequestParam("id") @ApiIgnore Integer id){
        LimitedTimeFlashDeal limitedTimeFlashDeal = this.timeLimitedSecondKillService.queryById(id);
        return new Result<LimitedTimeFlashDeal>().ok(limitedTimeFlashDeal);
    }

    @GetMapping("/deleteById")
    @ApiOperation("根据id删除一条/多条数据")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "ids",value ="药品ids",dataType ="Array")
    })
    public Result deleteById(@RequestParam @ApiIgnore List<String> ids){
        Integer integer = this.timeLimitedSecondKillService.deleteById(ids);
        if(integer!=-1){
            return new Result().ok("删除成功");
        }
        return new Result().error("删除失败");
    }
    @ApiOperation("新增->选择商品/ ->请输入商品名")
    @GetMapping("/queryAllDrugInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "drugName",value = "商品名字",dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "page",value = "当前页",dataType = "Integer")
    })
    public Result<List> queryDrugInfo(@RequestParam @ApiIgnore Map<String,Object> map){
        return null;// new Result<>().ok();
    }
//    @ApiOperation("新增->选择商品->搜索")
//    @GetMapping("/queryAllDrugInfo")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "query",name = "drugName",value = "商品名字",dataType = "String")
//    })
//    public Result<List> queryDrugInfoByName(@RequestParam){
//        return null;// new Result<>().ok();
//    }
    @PostMapping("/saveData")
    @ApiOperation("新增/保存编辑")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "query",name = "ids",value ="药品ids",dataType ="Array")
//    })
    public Result saveData(@RequestBody LimitedTimeFlashDeal limitedTimeFlashDeal){
        Integer success = null;
        if(limitedTimeFlashDeal.getId()!=null&&limitedTimeFlashDeal.getId()!=""){
            success = this.timeLimitedSecondKillService.updateData(limitedTimeFlashDeal);
            return success==-1?new Result().error("编辑失败"):new Result().ok("编辑成功");
        }
        UUID uuid = UUID.randomUUID();
        limitedTimeFlashDeal.setId(uuid.toString().substring(0,8).replaceAll("-","6"));
        success = this.timeLimitedSecondKillService.saveData(limitedTimeFlashDeal);
        return success==-1?new Result().error("新增失败"):new Result().ok("新增成功");
    }

}
