package com.gxa.modules.sys.controller.goodsController;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.goods.Drug;
import com.gxa.modules.sys.entity.goods.Medicinal;
import com.gxa.modules.sys.entity.goods.Symptom;
import com.gxa.modules.sys.form.goods.CheckForm;
import com.gxa.modules.sys.form.goods.DrugForm;
import com.gxa.modules.sys.form.goods.MedicinalForm;
import com.gxa.modules.sys.form.goods.SymptomForm;
import com.gxa.modules.sys.service.goods.DrugService;
import com.gxa.modules.sys.service.goods.MedicinalService;
import com.gxa.modules.sys.service.goods.SymptomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

@Api(tags = "商品接口")//42个接口
@RestController
@Slf4j
public class GoodsController {

    /**
     * 商品-
     * 药品分类
     *
     *
     */

    @Autowired
    private MedicinalService medicinalService;


    @ApiOperation(value="药品分类，全部数据，分页查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前是第几页",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "limit",value ="每页显示多少条",dataType ="int"),
//            @ApiImplicitParam(paramType = "query",name = "username",value ="查询条件",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "order",value ="升序asc，降序填desc",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "sidx",value ="排序字段",dataType ="String"),

    })
    @GetMapping("/medicinal/list")
    public Result medicinalList(@RequestParam @ApiIgnore Map<String,Object> params){

        PageUtils list = this.medicinalService.List(params);
        return new Result().ok(list);
    }

    @ApiOperation(value="药品分类，编辑接口")
    @PostMapping("/medicinal/update")
    public Result medicinalUpdate(@RequestBody Medicinal medicinal){

        Date date = new Date();
        medicinal.setAddTime(date);

        Map map = new HashMap();
        map.put("version",medicinal.getVersion());
        map.put("id",medicinal.getId());

        this.medicinalService.update(medicinal,new UpdateWrapper<Medicinal>().allEq(map));

        medicinal.setVersion(medicinal.getVersion()+1);
        this.medicinalService.update(medicinal,new UpdateWrapper<Medicinal>().eq("id",medicinal.getId()));
        Result<Medicinal> Result = new Result<>();
        return Result.ok();
    }

    @ApiOperation(value="药品分类，新增分类接口")
    @PutMapping("/medicinal/insert")
    public Result medicinalInsert(@RequestBody Medicinal medicinal){

        Date date = new Date();
        medicinal.setAddTime(date);

        medicinal.setRank("一级");
        this.medicinalService.save(medicinal);

        return new Result().ok();
    }

    @ApiOperation(value="药品分类，新增下级接口")//新增的时候，上级id可能产生问题，字段不一致
    @PutMapping("/medicinal/insertRank")
    public Result medicinalInsertRank(@RequestBody Medicinal medicinal){

        Date date = new Date();
        medicinal.setAddTime(date);
        medicinal.setRank("二级");

        medicinal.setHigherLevel(medicinal.getId());
        this.medicinalService.save(medicinal);

        return new Result().ok();
    }

    @ApiOperation(value="药品分类，新增下级下拉框接口")
    @GetMapping("/medicinal/insertRank/select")
    public Result medicinalInsertRankSelect(){

        Date date = new Date(2022 - 12 - 1);
        List<Medicinal> medicinals = new ArrayList<>();
        Medicinal medicinal = new Medicinal(1, "1", "1", "1", "1", "1", date,1,1);
        boolean add = medicinals.add(medicinal);
        return new Result().ok(add);
    }

    @ApiOperation(value="药品分类，删除接口")
    @DeleteMapping("/medicinal/delete")
    public Result medicinalDelete(@RequestParam("id") int id){


        return new Result().ok();
    }

    @ApiOperation(value="药品分类，查看下级接口")
    @GetMapping("/medicinal/select")
    public Result medicinalSelect(@RequestParam("id") int id){

        Date date = new Date(2022 - 12 - 1);
        Medicinal medicinal = new Medicinal(1, "1", "1", "1", "1", "1", date,1,1);
        Result<Medicinal> Result = new Result<>();
        return new Result().ok(medicinal);
    }

    @ApiOperation(value="药品分类，批量删除接口")
    @DeleteMapping("/medicinal/deleteMore")
    public Result medicinalDeleteMore(@RequestBody String[] id){


        return new Result().ok();
    }








    /**
     * 商品-
     * 药品二级分类
     *
     *
     */

    @ApiOperation(value="药品分类，二级分类，分页查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前是第几页",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "limit",value ="每页显示多少条",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "username",value ="查询条件",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "order",value ="升序asc，降序填desc",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "sidx",value ="排序字段",dataType ="String"),

    })
    @GetMapping("/medicinal/two/list")
    public Result medicinalTwoList(@RequestParam @ApiIgnore Map<String,Object> params){

        Date date = new Date(2022 - 12 - 1);
        Medicinal medicinal = new Medicinal(1, "1", "1", "1", "1", "1", date,1,1);
        Result<Medicinal> Result = new Result<>();
        return Result.ok(medicinal);
    }

    @ApiOperation(value="药品分类，二级分类，新增分类接口")
    @PutMapping("/medicinal/two/insert")
    public Result medicinalTwoInsert(@RequestBody MedicinalForm medicinalForm){


        return new Result().ok();
    }

    @ApiOperation(value="药品分类，二级分类，编辑接口")
    @PostMapping("/medicinal/two/update")
    public Result medicinalTwoUpdate(@RequestBody MedicinalForm medicinalForm){

        Result<Medicinal> Result = new Result<>();
        return Result.ok();
    }

    @ApiOperation(value="药品分类，二级分类，删除接口")
    @DeleteMapping("/medicinal/two/delete")
    public Result medicinalTwoDelete(@RequestParam("id") int id){


        return new Result().ok();
    }


    @ApiOperation(value="药品分类，二级分类，批量删除接口")
    @DeleteMapping("/medicinal/two/deleteMore")
    public Result medicinalTwoDeleteMore(@RequestBody String[] id){


        return new Result().ok();
    }









    /**
     * 商品-
     * 症状分类
     *
     *
     */
    @Autowired
    private SymptomService symptomService;


    @ApiOperation(value="症状分类，分页查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前是第几页",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "limit",value ="每页显示多少条",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "username",value ="查询条件",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "order",value ="升序asc，降序填desc",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "sidx",value ="排序字段",dataType ="String"),

    })
    @GetMapping("/Symptom/list")
    public Result symptomList(@RequestParam @ApiIgnore Map<String,Object> params){

        Date date = new Date(2022 - 12 - 1);
        Symptom symptom = new Symptom(1, "1", "1", "1", "1", "1", date, 1);
        return new Result().ok(symptom);
    }

    @ApiOperation(value="症状分类，编辑接口")
    @PostMapping("/Symptom/update")
    public Result symptomUpdate(@RequestBody SymptomForm symptomForm){

        Result<Medicinal> Result = new Result<>();
        return Result.ok();
    }

    @ApiOperation(value="症状分类，新增分类接口")
    @PutMapping("/Symptom/insert")
    public Result symptomInsert(@RequestBody SymptomForm symptomForm){


        return new Result().ok();
    }

    @ApiOperation(value="症状分类，新增下级接口")
    @PutMapping("/Symptom/insertRank")
    public Result symptomInsertRank(@RequestBody SymptomForm symptomForm){


        return new Result().ok();
    }

    @ApiOperation(value="症状分类，新增下级下拉框接口")
    @GetMapping("/Symptom/insertRank/select")
    public Result symptomInsertRankSelect(){

        Date date = new Date(2022 - 12 - 1);
        List<Symptom> medicinals = new ArrayList<>();
        Symptom symptom = new Symptom(1, "1", "1", "1", "1", "1", date, 1);
        boolean add = medicinals.add(symptom);
        return new Result().ok(add);
    }

    @ApiOperation(value="症状分类，删除接口")
    @DeleteMapping("/Symptom/delete")
    public Result symptomDelete(@RequestParam("id") int id){


        return new Result().ok();
    }

    @ApiOperation(value="症状分类，查看下级接口")
    @GetMapping("/Symptom/select")
    public Result symptomSelect(@RequestParam("id") int id){

        Date date = new Date(2022 - 12 - 1);

        Symptom symptom = new Symptom(1, "1", "1", "1", "1", "1", date, 1);
        return new Result().ok(symptom);
    }


    @ApiOperation(value="症状分类，批量删除接口")
    @DeleteMapping("/Symptom/deleteMore")
    public Result symptomDeleteMore(@RequestBody String[] id){


        return new Result().ok();
    }










    /**
     * 商品-
     * 症状二级分类
     *
     *
     */
    @ApiOperation(value="症状分类，二级分类，分页查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前是第几页",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "limit",value ="每页显示多少条",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "username",value ="查询条件",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "order",value ="升序asc，降序填desc",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "sidx",value ="排序字段",dataType ="String"),

    })
    @GetMapping("/Symptom/two/list")
    public Result symptomTwoList(@RequestParam @ApiIgnore Map<String,Object> params){

        Date date = new Date(2022 - 12 - 1);

        Symptom symptom = new Symptom(1, "1", "1", "1", "1", "1", date, 1);
        return new Result().ok(symptom);
    }

    @ApiOperation(value="症状分类，二级分类，新增分类接口")
    @PutMapping("/Symptom/two/insert")
    public Result symptomTwoInsert(@RequestBody SymptomForm symptomForm){


        return new Result().ok();
    }

    @ApiOperation(value="症状分类，二级分类，编辑接口")
    @PostMapping("/Symptom/two/update")
    public Result symptomTwoUpdate(@RequestBody SymptomForm symptomForm){

        Result<Medicinal> Result = new Result<>();
        return Result.ok();
    }

    @ApiOperation(value="症状分类，二级分类，删除接口")
    @DeleteMapping("/Symptom/two/delete")
    public Result symptomTwoDelete(@RequestParam("id") int id){


        return new Result().ok();
    }


    @ApiOperation(value="症状分类，二级分类，批量删除接口")
    @DeleteMapping("/Symptom/two/deleteMore")
    public Result symptomTwoDeleteMore(@RequestBody String[] id){


        return new Result().ok();
    }









    /**
     * 商品-
     * 药品管理
     *
     *
     */
    @Autowired
    private DrugService drugService;


    @ApiOperation(value="药品管理，分页查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前是第几页",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "limit",value ="每页显示多少条",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "username",value ="查询条件",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "order",value ="升序asc，降序填desc",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "sidx",value ="排序字段",dataType ="String"),

    })
    @GetMapping("/drug/list")
    public Result drugList(@RequestParam @ApiIgnore Map<String,Object> params){

        Date date = new Date(2022 - 12 - 1);
        Drug drug = new Drug(1, "1", "1", 2.2, "1", "1", "1", "1", date, "1", "1", "1", 1, 1, "1", 1, "1", "1", "1", "1", "1", "1", "1", 1,"1");
        return new Result().ok(drug);
    }

    @ApiOperation(value="药品管理，筛选，新增，添加时，药品分类和症状的下拉框接口")
    @GetMapping("/drug/screenSelect")
    public Result drugScreenSelect(){


        Date date = new Date(2022 - 12 - 1);
        Drug drug = new Drug(1, "1", "1", 2.2, "1", "1", "1", "1", date, "1", "1", "1", 1, 1, "1", 1, "1", "1", "1", "1", "1", "1", "1", 1,"1");
        return new Result().ok(drug);
    }

    @ApiOperation(value="药品管理，数据列表根据状态查询接口")    //要根据传过来的条件判断怎么查
    @GetMapping("/drug/screenSelectBy")
    public Result drugScreenSelectBy(@RequestBody DrugForm drugForm){


        Date date = new Date(2022 - 12 - 1);
        Drug drug = new Drug(1, "1", "1", 2.2, "1", "1", "1", "1", date, "1", "1", "1", 1, 1, "1", 1, "1", "1", "1", "1", "1", "1", "1", 1,"1");
        return new Result().ok(drug);
    }

    @ApiOperation(value="药品管理，查看接口")
    @GetMapping("/drug/select")
    public Result drugSelect(@RequestParam("id") int id){

        Date date = new Date(2022 - 12 - 1);
        Drug drug = new Drug(1, "1", "1", 2.2, "1", "1", "1", "1", date, "1", "1", "1", 1, 1, "1", 1, "1", "1", "1", "1", "1", "1", "1", 1,"1");
        return new Result().ok(drug);
    }

    @ApiOperation(value="药品管理，编辑接口")
    @PostMapping("/drug/update")
    public Result drugUpdate(@RequestBody DrugForm drugForm){

        Result<Medicinal> Result = new Result<>();
        return Result.ok();
    }

    @ApiOperation(value="药品管理，删除接口")
    @DeleteMapping("/drug/delete")
    public Result drugDelete(@RequestParam("id") int id){


        return new Result().ok();
    }

    @ApiOperation(value="药品管理，新增药品接口")
    @PutMapping("/drug/insert")
    public Result drugInsert(@RequestBody DrugForm drugForm){


        return new Result().ok();
    }


    @ApiOperation(value="药品管理，筛选接口")
    @GetMapping("/drug/screen")
    public Result drugInsertRank(@RequestBody DrugForm drugForm){


        return new Result().ok();
    }

    @ApiOperation(value="药品管理，批量删除接口")
    @DeleteMapping("/drug/deleteMore")
    public Result drugDeleteMore(@RequestBody String[] id){


        return new Result().ok();
    }

    @ApiOperation(value="药品管理，根据id修改上架状态接口")
    @PostMapping("/drug/updateByid")
    public Result drugUpdateByid(@RequestBody int id){


        return new Result().ok();
    }











    /**
     * 商品-
     * 药品审核
     *
     *
     */
    @ApiOperation(value="药品审核，分页查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前是第几页",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "limit",value ="每页显示多少条",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "username",value ="查询条件",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "order",value ="升序asc，降序填desc",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "sidx",value ="排序字段",dataType ="String"),

    })
    @GetMapping("/check/list")//查询的数据是未审核的
    public Result checkList(@RequestParam @ApiIgnore Map<String,Object> params){

        Date date = new Date(2022 - 12 - 1);
        Drug drug = new Drug(1, "1", "1", 2.2, "1", "1", "1", "1", date, "1", "1", "1", 1, 1, "1", 1, "1", "1", "1", "1", "1", "1", "1", 1,"1");
        return new Result().ok(drug);
    }

    @ApiOperation(value="药品审核，查看接口")//可以直接调药品查看的接口
    @GetMapping("/check/select")
    public Result checkSelect(@RequestParam("id") int id){

        Date date = new Date(2022 - 12 - 1);
        Drug drug = new Drug(1, "1", "1", 2.2, "1", "1", "1", "1", date, "1", "1", "1", 1, 1, "1", 1, "1", "1", "1", "1", "1", "1", "1", 1,"1");
        return new Result().ok(drug);
    }

    @ApiOperation(value="药品审核，审核接口")
    @PostMapping("/check/update")
    public Result checkUpdate(@RequestBody CheckForm checkForm){

        Date date = new Date(2022 - 12 - 1);
        Drug drug = new Drug(1, "1", "1", 2.2, "1", "1", "1", "1", date, "1", "1", "1", 1, 1, "1", 1, "1", "1", "1", "1", "1", "1", "1", 1,"1");
        return new Result().ok(drug);
    }

    @ApiOperation(value="药品审核，筛选下拉框接口")//可以直接调药品管理的下拉框接口
    @GetMapping("/check/screenSelect")
    public Result checkScreenSelect(){


        Date date = new Date(2022 - 12 - 1);
        Drug drug = new Drug(1, "1", "1", 2.2, "1", "1", "1", "1", date, "1", "1", "1", 1, 1, "1", 1, "1", "1", "1", "1", "1", "1", "1", 1,"1");
        return new Result().ok(drug);
    }

    @ApiOperation(value="药品审核，筛选接口")//要加一个条件，，，，，审核状态是未审核
    @GetMapping("/check/screen")
    public Result checkScreen(@RequestBody DrugForm drugForm){

        Date date = new Date(2022 - 12 - 1);
        Drug drug = new Drug(1, "1", "1", 2.2, "1", "1", "1", "1", date, "1", "1", "1", 1, 1, "1", 1, "1", "1", "1", "1", "1", "1", "1", 1,"1");
        return new Result().ok(drug);
    }

    @ApiOperation(value="药品审核，批量删除接口")
    @DeleteMapping("/check/deleteMore")
    public Result checkDeleteMore(@RequestBody String[] id){


        return new Result().ok();
    }



}
