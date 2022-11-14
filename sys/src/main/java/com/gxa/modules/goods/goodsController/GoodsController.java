package com.gxa.modules.goods.goodsController;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.goods.goodsEntity.Drug;
import com.gxa.modules.goods.goodsEntity.Medicinal;
import com.gxa.modules.goods.goodsEntity.Symptom;
import com.gxa.modules.goods.goodsForm.CheckForm;
import com.gxa.modules.goods.goodsForm.DrugForm;
import com.gxa.modules.goods.goodsForm.MedicinalForm;
import com.gxa.modules.goods.goodsForm.SymptomForm;
import com.gxa.modules.goods.goodsService.DrugService;
import com.gxa.modules.goods.goodsService.MedicinalService;
import com.gxa.modules.goods.goodsService.SymptomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

@Api(tags = "商品接口")
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

        PageUtils list = this.medicinalService.list(params);
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

        medicinal.setId(UUID.randomUUID().toString());
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

        medicinal.setId(UUID.randomUUID().toString());

//        medicinal.setHigherLevel(medicinal.getId());
        this.medicinalService.save(medicinal);

        return new Result().ok();
    }

    @ApiOperation(value="药品分类，新增下级下拉框接口")
    @GetMapping("/medicinal/insertRank/select")
    public Result medicinalInsertRankSelect(){


        List<Medicinal> medicinals = this.medicinalService.list(new QueryWrapper<Medicinal>().eq("`rank`", "一级"));
        return new Result().ok(medicinals);
    }

    @ApiOperation(value="药品分类，删除接口")
    @DeleteMapping("/medicinal/delete")
    public Result medicinalDelete(@RequestParam("id") int id){

        this.medicinalService.removeById(id);
        return new Result().ok();
    }

    @ApiOperation(value="药品分类，查看下级接口")
    @GetMapping("/medicinal/select")
    public Result medicinalSelect(@RequestParam("id") int id){

        List<Medicinal> medicinals = this.medicinalService.list(new QueryWrapper<Medicinal>().eq("higher_level", id));
        return new Result().ok(medicinals);
    }

    @ApiOperation(value="药品分类，批量删除接口")
    @DeleteMapping("/medicinal/deleteMore")
    public Result medicinalDeleteMore(@RequestBody List<String> id){

        this.medicinalService.removeByIds(id);
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
//            @ApiImplicitParam(paramType = "query",name = "username",value ="查询条件",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "order",value ="升序asc，降序填desc",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "sidx",value ="排序字段",dataType ="String"),

    })
    @GetMapping("/medicinal/two/list")
    public Result medicinalTwoList(@RequestParam @ApiIgnore Map<String,Object> params){

        PageUtils pageUtils = this.medicinalService.listTwo(params);
        return new Result().ok(pageUtils);
    }

    @ApiOperation(value="药品分类，二级分类，新增分类下拉框接口")
    @GetMapping("/medicinal/two/insertRank/select")
    public Result medicinalTwoInsertRankSelect(){


        List<Medicinal> medicinals = this.medicinalService.list(new QueryWrapper<Medicinal>().eq("`rank`", "一级"));
        return new Result().ok(medicinals);
    }

    @ApiOperation(value="药品分类，二级分类，新增分类接口")//为了保持幂等性可以将业务ID向传给前端
    @PutMapping("/medicinal/two/insert")
    public Result medicinalTwoInsert(@RequestBody Medicinal medicinal){

        Date date = new Date();
        medicinal.setAddTime(date);
        medicinal.setRank("二级");

        medicinal.setId(UUID.randomUUID().toString());

//        medicinal.setHigherLevel(medicinal.getId());
        this.medicinalService.save(medicinal);

        return new Result().ok();
    }

    @ApiOperation(value="药品分类，二级分类，编辑接口")//提醒前端传过来的上级id字段名写成higher_level
    @PostMapping("/medicinal/two/update")
    public Result medicinalTwoUpdate(@RequestBody Medicinal medicinal){

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

    @ApiOperation(value="药品分类，二级分类，删除接口")
    @DeleteMapping("/medicinal/two/delete")
    public Result medicinalTwoDelete(@RequestParam("id") int id){

        this.medicinalService.removeById(id);
        return new Result().ok();
    }


    @ApiOperation(value="药品分类，二级分类，批量删除接口")
    @DeleteMapping("/medicinal/two/deleteMore")
    public Result medicinalTwoDeleteMore(@RequestBody List<String> id){

        this.medicinalService.removeByIds(id);
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
            @ApiImplicitParam(paramType = "query",name = "order",value ="升序asc，降序填desc",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "sidx",value ="排序字段",dataType ="String"),

    })
    @GetMapping("/Symptom/list")
    public Result symptomList(@RequestParam @ApiIgnore Map<String,Object> params){

        PageUtils list = this.symptomService.list(params);
        return new Result().ok(list);
    }

    @ApiOperation(value="症状分类，编辑接口")
    @PostMapping("/Symptom/update")
    public Result symptomUpdate(@RequestBody Symptom symptom){


        Date date = new Date();
        symptom.setAddTime(date);

        Map map = new HashMap();
        map.put("version",symptom.getVersion());
        map.put("id",symptom.getId());

        this.symptomService.update(symptom,new UpdateWrapper<Symptom>().allEq(map));

        symptom.setVersion(symptom.getVersion()+1);
        this.symptomService.update(symptom,new UpdateWrapper<Symptom>().eq("id",symptom.getId()));
        Result<Medicinal> Result = new Result<>();
        return Result.ok();
    }

    @ApiOperation(value="症状分类，新增分类接口")
    @PutMapping("/Symptom/insert")
    public Result symptomInsert(@RequestBody Symptom symptom){

        Date date = new Date();
        symptom.setAddTime(date);

        symptom.setId(UUID.randomUUID().toString());
        symptom.setRank("一级");
        this.symptomService.save(symptom);

        return new Result().ok();
    }

    @ApiOperation(value="症状分类，新增下级接口")
    @PutMapping("/Symptom/insertRank")
    public Result symptomInsertRank(@RequestBody Symptom symptom){

        Date date = new Date();
        symptom.setAddTime(date);
        symptom.setRank("二级");

        symptom.setId(UUID.randomUUID().toString());

//        medicinal.setHigherLevel(medicinal.getId());
        this.symptomService.save(symptom);

        return new Result().ok();
    }

    @ApiOperation(value="症状分类，新增下级下拉框接口")
    @GetMapping("/Symptom/insertRank/select")
    public Result symptomInsertRankSelect(){

        List<Symptom> list = this.symptomService.list(new QueryWrapper<Symptom>().eq("`rank`", "一级"));
        return new Result().ok(list);
    }

    @ApiOperation(value="症状分类，删除接口")
    @DeleteMapping("/Symptom/delete")
    public Result symptomDelete(@RequestParam("id") int id){

        this.symptomService.removeById(id);
        return new Result().ok();
    }

    @ApiOperation(value="症状分类，查看下级接口")
    @GetMapping("/Symptom/select")
    public Result symptomSelect(@RequestParam("id") int id){

        List<Symptom> list = this.symptomService.list(new QueryWrapper<Symptom>().eq("higher_level", id));
        return new Result().ok(list);
    }


    @ApiOperation(value="症状分类，批量删除接口")
    @DeleteMapping("/Symptom/deleteMore")
    public Result symptomDeleteMore(@RequestBody List<String> id){

        this.symptomService.removeByIds(id);
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
            @ApiImplicitParam(paramType = "query",name = "order",value ="升序asc，降序填desc",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "sidx",value ="排序字段",dataType ="String")

    })
    @GetMapping("/Symptom/two/list")
    public Result symptomTwoList(@RequestParam @ApiIgnore Map<String,Object> params){

        PageUtils pageUtils = this.symptomService.listTwo(params);
        return new Result().ok(pageUtils);
    }

    @ApiOperation(value="症状分类，二级分类，新增分类接口")
    @PutMapping("/Symptom/two/insert")
    public Result symptomTwoInsert(@RequestBody Symptom symptom){

        Date date = new Date();
        symptom.setAddTime(date);

        symptom.setId(UUID.randomUUID().toString());
        symptom.setRank("二级");
        this.symptomService.save(symptom);

        return new Result().ok();
    }

    @ApiOperation(value="症状分类，二级分类，新增分类下拉框接口")
    @GetMapping("/Symptom/two/insertRank/select")
    public Result symptomTwoInsertRankSelect(){


        List<Symptom> symptoms = this.symptomService.list(new QueryWrapper<Symptom>().eq("`rank`", "一级"));
        return new Result().ok(symptoms);
    }

    @ApiOperation(value="症状分类，二级分类，编辑接口")
    @PostMapping("/Symptom/two/update")
    public Result symptomTwoUpdate(@RequestBody Symptom symptom){

        Date date = new Date();
        symptom.setAddTime(date);

        Map map = new HashMap();
        map.put("version",symptom.getVersion());
        map.put("id",symptom.getId());

        this.symptomService.update(symptom,new UpdateWrapper<Symptom>().allEq(map));

        symptom.setVersion(symptom.getVersion()+1);
        this.symptomService.update(symptom,new UpdateWrapper<Symptom>().eq("id",symptom.getId()));
        Result<Medicinal> Result = new Result<>();
        return Result.ok();
    }

    @ApiOperation(value="症状分类，二级分类，删除接口")
    @DeleteMapping("/Symptom/two/delete")
    public Result symptomTwoDelete(@RequestParam("id") int id){

        this.symptomService.removeById(id);
        return new Result().ok();
    }


    @ApiOperation(value="症状分类，二级分类，批量删除接口")
    @DeleteMapping("/Symptom/two/deleteMore")
    public Result symptomTwoDeleteMore(@RequestBody List<String> id){

        this.symptomService.removeByIds(id);
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
            @ApiImplicitParam(paramType = "query",name = "order",value ="升序asc，降序填desc",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "sidx",value ="排序字段",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "drugName",value ="药品名称,查询条件",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "medicinalId",value ="药品分类ID，查询条件",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "symptomId",value ="症状分类ID，查询条件",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "shelves",value ="是否上架，0表示未上架，1表示已上架，查询条件",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "state",value ="是否审核，未通过,查询条件",dataType ="String"),
    })
    @GetMapping("/drug/list")
    public Result drugList(@RequestParam @ApiIgnore Map<String,Object> params){

        PageUtils list = this.drugService.list(params);
        return new Result().ok(list);
    }


    @ApiOperation(value="药品管理，筛选，新增，添加时，症状分类的下拉框接口")
    @GetMapping("/drug/symptom/screenSelect")
    public Result drugSymptomScreenSelect(){


        List<Symptom> symptoms = this.symptomService.list(new QueryWrapper<Symptom>().eq("`rank`", "一级"));
        return new Result().ok(symptoms);
    }

    @ApiOperation(value="药品管理，筛选，新增，添加时，二级症状分类的下拉框接口")
    @GetMapping("/drug/two/symptom/screenSelect")
    public Result drugTwoSymptomScreenSelect(@RequestParam("id") int id){

        List<Symptom> list = this.symptomService.list(new QueryWrapper<Symptom>().eq("higher_level", id));
        return new Result().ok(list);
    }

    @ApiOperation(value="药品管理，筛选，新增，添加时，药品分类的下拉框接口")
    @GetMapping("/drug/medicinal/screenSelect")
    public Result drugMedicinalScreenSelect(){


        List<Medicinal> medicinals = this.medicinalService.list(new QueryWrapper<Medicinal>().eq("`rank`", "一级"));
        return new Result().ok(medicinals);
    }

    @ApiOperation(value="药品管理，筛选，新增，添加时，二级药品分类的下拉框接口")
    @GetMapping("/drug/two/medicinal/screenSelect")
    public Result drugTwoMedicinalScreenSelect(@RequestParam("id") int id){

        List<Symptom> list = this.symptomService.list(new QueryWrapper<Symptom>().eq("higher_level", id));
        return new Result().ok(list);
    }

//    @ApiOperation(value="药品管理，数据列表根据状态查询接口")    //要根据传过来的条件判断怎么查
//    @GetMapping("/drug/screenSelectBy")
//    public Result drugScreenSelectBy(@RequestBody DrugForm drugForm){
//
//
//        Date date = new Date(2022 - 12 - 1);
//        Drug drug = new Drug("1", "1", "1", 2.2, "1", "1", "1", 1, date, "1", "1", "1", "1", "1", "1", 1, "1", "1", "1", "1", "1", "1", "1", 1,"1");
//        return new Result().ok(drug);
//    }

    @ApiOperation(value="药品管理，查看接口")
    @GetMapping("/drug/select")
    public Result drugSelect(@RequestParam("id") int id){

        Drug drug = this.drugService.getOne(new QueryWrapper<Drug>().eq("id", id));

        //找到药品分类，拼成字符串
        Medicinal medicinal = this.medicinalService.getById(drug.getMedicinalId());
        String categoryName1 = medicinal.getCategoryName();
        Medicinal medicinalServiceById = this.medicinalService.getById(medicinal.getHigherLevel());
        String categoryName = medicinalServiceById.getCategoryName();
        drug.setMedicinal(categoryName+">"+categoryName1);

        //找到症状分类，拼成字符串
        Symptom symptom = this.symptomService.getById(drug.getSymptomId());
        String symptomName1 = symptom.getSymptomName();
        Symptom byId = this.symptomService.getById(symptom.getHigherLevel());
        String symptomName = byId.getSymptomName();
        drug.setSymptom(symptomName+">"+symptomName1);

        return new Result().ok(drug);
    }

    @ApiOperation(value="药品管理，编辑接口")
    @PostMapping("/drug/update")
    public Result drugUpdate(@RequestBody Drug drug){

        this.drugService.update(drug,new UpdateWrapper<Drug>().eq("id",drug.getId()).eq("`version`",drug.getVersion()));
        drug.setVersion(drug.getVersion()+1);
        this.drugService.update(drug,new UpdateWrapper<Drug>().eq("id",drug.getId()));
        return new Result().ok();
    }

    @ApiOperation(value="药品管理，删除接口")
    @DeleteMapping("/drug/delete")
    public Result drugDelete(@RequestParam("id") int id){

        this.drugService.removeById(id);
        return new Result().ok();
    }

    @ApiOperation(value="药品管理，新增药品接口")
    @PutMapping("/drug/insert")
    public Result drugInsert(@RequestBody Drug drug){

        drug.setId(UUID.randomUUID().toString());
        drug.setState("待审核");
        this.drugService.save(drug);
        return new Result().ok();
    }

    @ApiOperation(value="药品管理，批量删除接口")
    @DeleteMapping("/drug/deleteMore")
    public Result drugDeleteMore(@RequestBody List<String> id){

        this.drugService.removeByIds(id);
        return new Result().ok();
    }

    @ApiOperation(value="药品管理，修改上架状态接口")
    @PostMapping("/drug/updateByid")
    public Result drugUpdateByid(@RequestBody Drug drug){

        if(drug.getShelves().equals("0")){
            drug.setShelves("1");
            this.drugService.update(drug,new UpdateWrapper<Drug>().eq("id",drug.getId()));
        }else {
            drug.setShelves("0");
            this.drugService.update(drug,new UpdateWrapper<Drug>().eq("id",drug.getId()));
        }
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
            @ApiImplicitParam(paramType = "query",name = "order",value ="升序asc，降序填desc",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "sidx",value ="排序字段",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "drugName",value ="药品名称,查询条件",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "medicinalId",value ="药品分类ID，查询条件",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "symptomId",value ="症状分类ID，查询条件",dataType ="String")
    })
    @GetMapping("/check/list")//查询的数据是待审核的
    public Result checkList(@RequestParam @ApiIgnore Map<String,Object> params){

        PageUtils list = this.drugService.list(params);
        return new Result().ok(list);
    }

    @ApiOperation(value="药品审核，查看接口")//可以直接调药品查看的接口
    @GetMapping("/check/select")
    public Result checkSelect(@RequestParam("id") String id){

        Drug drug = this.drugService.getOne(new QueryWrapper<Drug>().eq("id", id));

        //找到药品分类，拼成字符串
        Medicinal medicinal = this.medicinalService.getById(drug.getMedicinalId());
        String categoryName1 = medicinal.getCategoryName();
        Medicinal medicinalServiceById = this.medicinalService.getById(medicinal.getHigherLevel());
        String categoryName = medicinalServiceById.getCategoryName();
        drug.setMedicinal(categoryName+">"+categoryName1);

        //找到症状分类，拼成字符串
        Symptom symptom = this.symptomService.getById(drug.getSymptomId());
        String symptomName1 = symptom.getSymptomName();
        Symptom byId = this.symptomService.getById(symptom.getHigherLevel());
        String symptomName = byId.getSymptomName();
        drug.setSymptom(symptomName+">"+symptomName1);

        return new Result().ok(drug);
    }

    @ApiOperation(value="药品审核，审核接口")
    @PostMapping("/check/update")
    public Result checkUpdate(@RequestBody Drug drug){

        this.drugService.update(drug,new UpdateWrapper<Drug>().eq("`state`",drug.getState())
                .eq(StringUtils.isNotEmpty(drug.getRemarks()),"remarks",drug.getRemarks()));
        return new Result().ok();
    }


    @ApiOperation(value="药品审核，筛选，症状分类的下拉框接口")
    @GetMapping("/check/symptom/screenSelect")
    public Result checkSymptomScreenSelect(){


        List<Symptom> symptoms = this.symptomService.list(new QueryWrapper<Symptom>().eq("`rank`", "一级"));
        return new Result().ok(symptoms);
    }

    @ApiOperation(value="药品审核，筛选，二级症状分类的下拉框接口")
    @GetMapping("/check/two/symptom/screenSelect")
    public Result checkTwoSymptomScreenSelect(@RequestParam("id") int id){

        List<Symptom> list = this.symptomService.list(new QueryWrapper<Symptom>().eq("higher_level", id));
        return new Result().ok(list);
    }

    @ApiOperation(value="药品审核，筛选，药品分类的下拉框接口")
    @GetMapping("/check/medicinal/screenSelect")
    public Result checkMedicinalScreenSelect(){


        List<Medicinal> medicinals = this.medicinalService.list(new QueryWrapper<Medicinal>().eq("`rank`", "一级"));
        return new Result().ok(medicinals);
    }

    @ApiOperation(value="药品管理，二级药品分类的下拉框接口")
    @GetMapping("/check/two/medicinal/screenSelect")
    public Result checkTwoMedicinalScreenSelect(@RequestParam("id") int id){

        List<Symptom> list = this.symptomService.list(new QueryWrapper<Symptom>().eq("higher_level", id));
        return new Result().ok(list);
    }

    @ApiOperation(value="药品审核，批量删除接口")
    @DeleteMapping("/check/deleteMore")
    public Result checkDeleteMore(@RequestBody List<String> id){

        this.drugService.removeByIds(id);
        return new Result().ok();
    }



}
