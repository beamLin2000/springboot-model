package com.gxa.modules.sys.controller.backStage.promotion.eventManagement;

import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.backStage.promotion.eventManagment.EventManagement;
import com.gxa.modules.sys.service.promotion.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author :林溪
 * @date : 2022/11/11 14:12
 */
@RestController
@Api(tags = "活动管理")
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventService eventService;

    @ApiOperation("筛选/首页列表")
    @GetMapping("/search")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityTitle",value = "活动名称",dataType = "String"),
            @ApiImplicitParam(name = "status",value = "活动状态",dataType = "String"),
            @ApiImplicitParam(name = "page",value = "当前页",dataType = "Integer"),
    })
    public Result<PageUtils> search(@RequestParam @ApiIgnore Map<String,Object> map){
        PageUtils search = eventService.search(map);
        return new Result<PageUtils>().ok(search);
    }
    @ApiOperation("活动状态列表")
    @GetMapping("/queryStatus")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "若用于新增或编辑功能的下拉框,则应当传递任意非0字符",dataType = "String")
    })
    public Result<List<String>> queryStatus(@RequestParam(value = "id",defaultValue = "0")@ApiIgnore String id){
        List<String> status = new ArrayList<>();
        status.add("全部");
        status.add("未开始");
        status.add("进行中");
        if(id.equals("0")){
            status.add("已结束");
        }
        return new Result<List<String>>().ok(status);
    }

    @ApiOperation("上架状态修改")
    @PutMapping("/updateStatus/{id}/{status}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "需要修改状态的id",dataType = "String"),
            @ApiImplicitParam(name = "status",value = "当前按钮状态",dataType = "Integer")
    })
    public Result updateStatus(@PathVariable("id") @ApiIgnore String id,
                               @PathVariable("status")@ApiIgnore Integer status){
        Integer integer = eventService.updateStatus(id, status);
        return integer!=-1?new Result().ok("修改成功"):new Result().error("修改失败");
    }

    @ApiOperation("编辑pre")
    @GetMapping("/updateById/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "即将被修改的数据id",dataType = "String")
    })
    public Result selectById(@PathVariable("id")@ApiIgnore String id){
        EventManagement eventManagement = eventService.selectById(id);
        if(eventManagement!=null){
            return new Result().ok(eventManagement);
    }
        return new Result().error("数据未找到");
    }
    @ApiOperation("批量/删除")
    @DeleteMapping("/deleteByIds")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids",value = "需要被删除的id集合",dataType = "array")
    })
    public Result deleteByIds(@RequestBody @ApiIgnore List<String> ids){
        Integer integer = eventService.deleteByIds(ids);
        if(integer!=-1){
            return new Result().ok("删除成功");
        }
        return new Result().error("删除失败");
    }
//    @ApiOperation("新增活动->状态修改")
//    @PutMapping("/updateStatus/{id}/{status}")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "需要被修改状态的id",dataType = "String"),
//            @ApiImplicitParam(name = "status",value = "当前的状态",dataType = "Integer")
//    })
//    public Result updateStatus(@PathVariable("id") @ApiIgnore String id,
//                               @PathVariable("status")@ApiIgnore Integer status){
//        eventService.
//
//    }
    @ApiOperation("新增活动->保存")
    @PostMapping("/save")
    public Result save(@RequestBody EventManagement eventManagement){
        Result r = new Result();
        if(eventManagement.getId()!=null&&eventManagement.getId().equals("")){
            Integer integer = eventService.saveData(eventManagement);
            return integer!=-1?r.ok("修改成功"):r.error("修改失败");
        }else{
            Integer integer = eventService.updateData(eventManagement);
            return integer!=-1?r.ok("新增成功"):r.error("新增失败");
        }
    }

}
