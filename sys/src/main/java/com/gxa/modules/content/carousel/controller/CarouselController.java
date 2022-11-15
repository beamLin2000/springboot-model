package com.gxa.modules.content.carousel.controller;


import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.content.carousel.entity.Carousel;
import com.gxa.modules.content.carousel.service.CarouselService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


import java.util.List;
import java.util.Map;
//内容——首页轮播图管理
@Api(tags = "内容")
@RestController
@Slf4j
public class CarouselController {


        @Autowired
        private CarouselService carouselService;

    /**
     *
     * @param params
     * @return
     */
    @ApiOperation("内容查询接口")
    @GetMapping("/carousel/list")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前是第几页",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "limit",value ="每页显示多少条",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "order",value ="升序asc，降序填desc",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "sidx",value ="排序字段",dataType ="String"),
    })
    public Result<PageUtils> carouselList(@RequestParam @ApiIgnore Map<String,Object> params){
        PageUtils pageUtils = this.carouselService.queryByPage01(params);
        return new Result<PageUtils>().ok(pageUtils);
    }

    /**
     *
     * @param params
     * @return
     */
    @ApiOperation("条件查询接口")
    @GetMapping("/carousel/list01")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前是第几页",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "limit",value ="每页显示多少条",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "order",value ="升序asc，降序填desc",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "sidx",value ="排序字段",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "carouselTitle",value ="根据标题查询",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "beginTime",value ="开始时间",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "endTime",value ="结束时间",dataType ="String"),
    })
    public Result<PageUtils> listByXx(@RequestParam @ApiIgnore Map<String,Object> params){
        PageUtils pageUtils = this.carouselService.queryByPageByCarouselTitle(params);
        return new Result<PageUtils>().ok(pageUtils);
    }

    /**
     *
     * @param carousel
     * @return
     */
    @ApiOperation("轮播图添加接口")
    @PostMapping("/carousel/add")
    public Result carouselAdd(@RequestBody Carousel carousel){
        this.carouselService.carouselAdd(carousel);
        return new Result().ok();
    }

    /**
     *
     * @param id
     * @return
     */
    @ApiOperation("轮播图根据id删除接口")
    @DeleteMapping("/carousel/delete")
    public Result carouselDelete(@RequestParam("id") Integer id){
        this.carouselService.carouselDelete(id);
        return new Result().ok();
    }

    /**
     *
     * @param carousel
     * @return
     */
    @ApiOperation("轮播图批量删除接口")
    @DeleteMapping("/carousel/delete01")
    public Result carouselDelete01(@RequestBody  List<Carousel> carousel){
        this.carouselService.deleteByidBatch(carousel);
        return new Result().ok();
    }

    /**
     *
     * @param carousel
     * @return
     */
    @ApiOperation("轮播图根据id修改")
    @PutMapping("/carousel/update")
    public Result carouselUpdate(@RequestBody Carousel carousel){
        this.carouselService.carouselUpdate(carousel);
        return new Result().ok();
    }


}
