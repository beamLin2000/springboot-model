package com.gxa.modules.goods.goodsController;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gxa.common.utils.Base64Utils;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.RedisUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.goods.goodsEntity.Drug;
import com.gxa.modules.goods.goodsEntity.Medicinal;
import com.gxa.modules.goods.goodsEntity.Symptom;
import com.gxa.modules.goods.goodsService.DrugService;
import com.gxa.modules.goods.goodsService.MedicinalService;
import com.gxa.modules.goods.goodsService.SymptomService;
import com.gxa.modules.login.entity.SysUser;
import com.gxa.modules.login.entity.User;
import com.gxa.modules.login.service.UserTokenService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Api(tags = "后台——商品接口")
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

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserTokenService userTokenService;


    @ApiOperation(value="药品分类，全部数据，分页查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前是第几页",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "limit",value ="每页显示多少条",dataType ="int"),
//            @ApiImplicitParam(paramType = "query",name = "username",value ="查询条件",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "order",value ="升序asc，降序填desc",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "sidx",value ="排序字段",dataType ="String"),

    })
    @ApiResponses({
            @ApiResponse( code = 200,message = "ok",response = Medicinal.class)
    })
    @GetMapping("/medicinal/list")
    public Result medicinalList(@RequestParam @ApiIgnore Map<String,Object> params){
        PageUtils list = this.medicinalService.list(params);
        return new Result().ok(list);
    }

    @ApiOperation(value="药品分类，编辑接口")
    @PostMapping("/medicinal/update")
    public Result medicinalUpdate(@RequestBody Medicinal medicinal,HttpServletRequest httpServletRequest){
        this.medicinalService.medicinalUpdate(medicinal,httpServletRequest);
        return new Result().ok();
    }

    @ApiOperation(value="药品分类，新增分类接口")
    @PutMapping("/medicinal/insert")
    public Result medicinalInsert(@RequestBody Medicinal medicinal,HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        SysUser sysUser = this.userTokenService.validateSysUserToken(token);
        medicinal.setUploader(sysUser.getUsername());
        this.medicinalService.medicinalInsert(medicinal);
        return new Result().ok();
    }

    @ApiOperation(value="药品分类，新增分类时返回id，接口")
    @GetMapping("/medicinal/insertBackId")
    public Result medicinalInsertBackId(){
        String string = UUID.randomUUID().toString();
        return new Result().ok(string);
    }

    @ApiOperation(value="药品分类，新增下级接口")//新增的时候，上级id可能产生问题，字段不一致
    @PutMapping("/medicinal/insertRank")
    public Result medicinalInsertRank(@RequestBody Medicinal medicinal,HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        SysUser sysUser = this.userTokenService.validateSysUserToken(token);
        medicinal.setUploader(sysUser.getUsername());
        this.medicinalService.medicinalInsertRank(medicinal);
        return new Result().ok();
    }

    @ApiOperation(value="药品分类，新增下级分类时返回id，接口")
    @GetMapping("/medicinal/insertRankBackId")
    public Result medicinalInsertRankBackId(){
        String string = UUID.randomUUID().toString();
        return new Result().ok(string);
    }

    @ApiOperation(value="药品分类，新增下级下拉框接口")
    @GetMapping("/medicinal/insertRank/select")
    public Result medicinalInsertRankSelect(){
        List<Medicinal> medicinals = this.medicinalService.list(new QueryWrapper<Medicinal>().eq("`rank`", "一级"));
        return new Result().ok(medicinals);
    }

    @ApiOperation(value="药品分类，删除接口")
    @DeleteMapping("/medicinal/delete")
    public Result medicinalDelete(@RequestParam("id") String id){
        //删除Redis中的数据
        List<Medicinal> medicinals = this.medicinalService.list(new QueryWrapper<Medicinal>().eq("higher_level", id));
        for (Medicinal i:medicinals
        ) {
            redisUtils.delete("Assort:"+ Base64Utils.encode(i.getCategoryName()));
        }

        this.medicinalService.removeById(id);
        this.medicinalService.remove(new QueryWrapper<Medicinal>().eq("higher_level",id));
        this.drugService.remove(new QueryWrapper<Drug>().eq("medicinal_id",id));
        return new Result().ok();
    }

    @ApiOperation(value="药品分类，查看下级接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前是第几页",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "limit",value ="每页显示多少条",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "order",value ="升序asc，降序填desc",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "sidx",value ="排序字段",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "id",value ="id",dataType ="String"),
    })
    @ApiResponses({
            @ApiResponse( code = 200,message = "ok",response = Medicinal.class)
    })
    @GetMapping("/medicinal/select")
    public Result medicinalSelect(@RequestParam @ApiIgnore Map<String,Object> params){
        PageUtils pageUtils = this.medicinalService.medicinalSelect(params);
        return new Result().ok(pageUtils);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body",name = "id",value ="删除的id",dataType ="Array"),
    })
    @ApiOperation(value="药品分类，批量删除接口")
    @DeleteMapping("/medicinal/deleteMore")
    public Result medicinalDeleteMore(@RequestBody @ApiIgnore List<String> id){
        //删除Redis中的数据
        for (int i=0;i<id.size();i++){
            List<Medicinal> medicinals = this.medicinalService.list(new QueryWrapper<Medicinal>().eq("higher_level", id.get(i)));
            for (Medicinal a:medicinals
            ) {
                redisUtils.delete("Assort:"+ Base64Utils.encode(a.getCategoryName()));
            }
        }

        this.medicinalService.removeByIds(id);
        for (String i:id
             ) {
            this.medicinalService.remove(new QueryWrapper<Medicinal>().eq("higher_level",i));
            this.drugService.remove(new QueryWrapper<Drug>().eq("medicinal_id",i));
        }

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
    @ApiResponses({
            @ApiResponse( code = 200,message = "ok",response = Medicinal.class)
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
    public Result medicinalTwoInsert(@RequestBody Medicinal medicinal,HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        SysUser sysUser = this.userTokenService.validateSysUserToken(token);
        medicinal.setUploader(sysUser.getUsername());
        this.medicinalService.medicinalTwoInsert(medicinal);
        return new Result().ok();
    }

    @ApiOperation(value="药品分类，二级分类，新增分类时返回id，接口")
    @GetMapping("/medicinal/two/insertBackId")
    public Result medicinalTwoInsertBackId(){
        String string = UUID.randomUUID().toString();
        return new Result().ok(string);
    }

    @ApiOperation(value="药品分类，二级分类，编辑接口")//提醒前端传过来的上级id字段名写成higher_level
    @PostMapping("/medicinal/two/update")
    public Result medicinalTwoUpdate(@RequestBody Medicinal medicinal,HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        SysUser sysUser = this.userTokenService.validateSysUserToken(token);
        medicinal.setUploader(sysUser.getUsername());
        this.medicinalService.medicinalTwoUpdate(medicinal);
        return new Result().ok();
    }

    @ApiOperation(value="药品分类，二级分类，删除接口")
    @DeleteMapping("/medicinal/two/delete")
    public Result medicinalTwoDelete(@RequestParam("id") String id){
        //删除Redis中的数据
        List<Medicinal> medicinals = this.medicinalService.list(new QueryWrapper<Medicinal>().eq("id", id));
        for (Medicinal i:medicinals
        ) {
            redisUtils.delete("Assort:"+ Base64Utils.encode(i.getCategoryName()));
        }

        this.medicinalService.removeById(id);
        this.drugService.remove(new QueryWrapper<Drug>().eq("medicinal_id",id));
        return new Result().ok();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body",name = "id",value ="删除的id",dataType ="Array"),
    })
    @ApiOperation(value="药品分类，二级分类，批量删除接口")
    @DeleteMapping("/medicinal/two/deleteMore")
    public Result medicinalTwoDeleteMore(@RequestBody @ApiIgnore List<String> id){
        //删除Redis中的数据
        for (int i=0;i<id.size();i++){
            List<Medicinal> medicinals = this.medicinalService.list(new QueryWrapper<Medicinal>().eq("id", id.get(i)));
            for (Medicinal a:medicinals
            ) {
                redisUtils.delete("Assort:"+ Base64Utils.encode(a.getCategoryName()));
            }
        }

        this.medicinalService.removeByIds(id);
        for (String i:id
             ) {
            this.drugService.remove(new QueryWrapper<Drug>().eq("medicinal_id",i));
        }
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
    @ApiResponses({
            @ApiResponse( code = 200,message = "ok",response = Symptom.class)
    })
    @GetMapping("/Symptom/list")
    public Result symptomList(@RequestParam @ApiIgnore Map<String,Object> params){
        PageUtils list = this.symptomService.list(params);
        return new Result().ok(list);
    }

    @ApiOperation(value="症状分类，编辑接口")
    @PostMapping("/Symptom/update")
    public Result symptomUpdate(@RequestBody Symptom symptom,HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        SysUser sysUser = this.userTokenService.validateSysUserToken(token);
        symptom.setUpload(sysUser.getUsername());
        this.symptomService.symptomUpdate(symptom);
        return new Result().ok();
    }

    @ApiOperation(value="症状分类，新增分类接口")
    @PutMapping("/Symptom/insert")
    public Result symptomInsert(@RequestBody Symptom symptom,HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        SysUser sysUser = this.userTokenService.validateSysUserToken(token);
        symptom.setUpload(sysUser.getUsername());
        this.symptomService.symptomInsert(symptom);
        return new Result().ok();
    }

    @ApiOperation(value="症状分类，新增分类时返回id，接口")
    @GetMapping("/Symptom/insertBackId")
    public Result symptomInsertBackId(){
        String string = UUID.randomUUID().toString();
        return new Result().ok(string);
    }

    @ApiOperation(value="症状分类，新增下级接口")
    @PutMapping("/Symptom/insertRank")
    public Result symptomInsertRank(@RequestBody Symptom symptom,HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        SysUser sysUser = this.userTokenService.validateSysUserToken(token);
        symptom.setUpload(sysUser.getUsername());
        this.symptomService.symptomInsertRank(symptom);
        return new Result().ok();
    }

    @ApiOperation(value="症状分类，新增下级，分类时返回id，接口")
    @GetMapping("/Symptom/insertRankBackId")
    public Result symptomInsertRankBackId(){
        String string = UUID.randomUUID().toString();
        return new Result().ok(string);
    }

    @ApiOperation(value="症状分类，新增下级下拉框接口")
    @GetMapping("/Symptom/insertRank/select")
    public Result symptomInsertRankSelect(){
        List<Symptom> list = this.symptomService.list(new QueryWrapper<Symptom>().eq("`rank`", "一级"));
        return new Result().ok(list);
    }

    @ApiOperation(value="症状分类，删除接口")
    @DeleteMapping("/Symptom/delete")
    public Result symptomDelete(@RequestParam("id") String id){
        this.symptomService.removeById(id);
        this.symptomService.remove(new QueryWrapper<Symptom>().eq("higher_level",id));
        this.drugService.remove(new QueryWrapper<Drug>().eq("symptom_id",id));
        return new Result().ok();
    }

    @ApiOperation(value="症状分类，查看下级接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前是第几页",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "limit",value ="每页显示多少条",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "order",value ="升序asc，降序填desc",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "sidx",value ="排序字段",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "id",value ="id",dataType ="String")
    })
    @ApiResponses({
            @ApiResponse( code = 200,message = "ok",response = Symptom.class)
    })
    @GetMapping("/Symptom/select")
    public Result symptomSelect(@RequestParam @ApiIgnore Map<String,Object> params){
        PageUtils pageUtils = this.symptomService.symptomSelect(params);
        return new Result().ok(pageUtils);
    }

    @ApiOperation(value="症状分类，批量删除接口")
    @DeleteMapping("/Symptom/deleteMore")
    public Result symptomDeleteMore(@RequestBody List<String> id){
        this.symptomService.removeByIds(id);
        for (String i:id
             ) {
            this.symptomService.remove(new QueryWrapper<Symptom>().eq("higher_level",i));
            this.drugService.remove(new QueryWrapper<Drug>().eq("symptom_id",i));
        }
        return new Result().ok();
    }


//    public static void main(String[] args) {
//        String[] a = {"1","2","3","4"};
//        for (int i = 0;i<=a.length-1;i++){
//            System.out.println(a[i]);
//        }
//
//
//    }






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
    @ApiResponses({
            @ApiResponse( code = 200,message = "ok",response = Symptom.class)
    })
    @GetMapping("/Symptom/two/list")
    public Result symptomTwoList(@RequestParam @ApiIgnore Map<String,Object> params){
        PageUtils pageUtils = this.symptomService.listTwo(params);
        return new Result().ok(pageUtils);
    }

    @ApiOperation(value="症状分类，二级分类，新增分类接口")
    @PutMapping("/Symptom/two/insert")
    public Result symptomTwoInsert(@RequestBody Symptom symptom,HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        SysUser sysUser = this.userTokenService.validateSysUserToken(token);
        symptom.setUpload(sysUser.getUsername());
        this.symptomService.symptomTwoInsert(symptom);
        return new Result().ok();
    }

    @ApiOperation(value="症状分类，二级分类，新增分类时返回id，接口")
    @GetMapping("/Symptom/two/insertBackId")
    public Result symptomTwoInsertBackId(){
        String string = UUID.randomUUID().toString();
        return new Result().ok(string);
    }

    @ApiOperation(value="症状分类，二级分类，新增分类下拉框接口")
    @GetMapping("/Symptom/two/insertRank/select")
    public Result symptomTwoInsertRankSelect(){
        List<Symptom> symptoms = this.symptomService.list(new QueryWrapper<Symptom>().eq("`rank`", "一级"));
        return new Result().ok(symptoms);
    }

    @ApiOperation(value="症状分类，二级分类，编辑接口")
    @PostMapping("/Symptom/two/update")
    public Result symptomTwoUpdate(@RequestBody Symptom symptom,HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        SysUser sysUser = this.userTokenService.validateSysUserToken(token);
        symptom.setUpload(sysUser.getUsername());
        this.symptomService.symptomTwoUpdate(symptom);
        return new Result().ok();
    }

    @ApiOperation(value="症状分类，二级分类，删除接口")
    @DeleteMapping("/Symptom/two/delete")
    public Result symptomTwoDelete(@RequestParam("id") String id){
        this.symptomService.removeById(id);
        this.drugService.remove(new QueryWrapper<Drug>().eq("symptom_id",id));
        return new Result().ok();
    }

    @ApiOperation(value="症状分类，二级分类，批量删除接口")
    @DeleteMapping("/Symptom/two/deleteMore")
    public Result symptomTwoDeleteMore(@RequestBody List<String> id){
        this.symptomService.removeByIds(id);
        for (String i:id
             ) {
            this.drugService.remove(new QueryWrapper<Drug>().eq("symptom_id",i));
        }
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
    @ApiResponses({
            @ApiResponse( code = 200,message = "ok",response = Drug.class)
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
    public Result drugTwoSymptomScreenSelect(@RequestParam("id") String id){
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
    public Result drugTwoMedicinalScreenSelect(@RequestParam("id") String id){
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
    public Result drugSelect(@RequestParam("id") String id){
        Drug drug = this.drugService.drugSelect(id);
        return new Result().ok(drug);
    }

    @ApiOperation(value="药品管理，编辑接口")
    @PostMapping("/drug/update")
    public Result drugUpdate(@RequestBody Drug drug){
        //删除Redis中的数据
        String categoryName = this.medicinalService.getById(drug.getMedicinalId()).getCategoryName();
        redisUtils.delete("Assort:"+ Base64Utils.encode(categoryName));

        this.drugService.update(drug,new UpdateWrapper<Drug>().eq("id",drug.getId()).eq("`version`",drug.getVersion()));
        drug.setVersion(drug.getVersion()+1);
        this.drugService.update(drug,new UpdateWrapper<Drug>().eq("id",drug.getId()));
        return new Result().ok();
    }

    @ApiOperation(value="药品管理，删除接口")
    @DeleteMapping("/drug/delete")
    public Result drugDelete(@RequestParam("id") String id){
        //删除Redis中的数据
        String medicinalId = this.drugService.getById(id).getMedicinalId();
        redisUtils.delete("Assort:"+ Base64Utils.encode(this.medicinalService.getById(medicinalId).getCategoryName()));

        this.drugService.removeById(id);
        return new Result().ok();
    }

    @ApiOperation(value="药品管理，新增药品接口")
    @PutMapping("/drug/insert")
    public Result drugInsert(@RequestBody Drug drug){
        drug.setState("待审核");
        this.drugService.save(drug);

        //删除Redis中的数据
        Medicinal medicinal = this.medicinalService.getById(drug.getMedicinalId());
        redisUtils.delete("Assort:"+ Base64Utils.encode(medicinal.getCategoryName()));
        return new Result().ok();
    }

    @ApiOperation(value="药品管理，新增药品时返回id,返回药品编码，接口")
    @GetMapping("/drug/insertBackId")
    public Result drugInsertBackId(){
        String id = UUID.randomUUID().toString();
        String drugCode = UUID.randomUUID().toString();
        List<String> a = new ArrayList<>();
        a.add(id);
        a.add(drugCode);
        return new Result().ok(a);
    }

    @ApiOperation(value="药品管理，批量删除接口")
    @DeleteMapping("/drug/deleteMore")
    public Result drugDeleteMore(@RequestBody List<String> id){
        //删除Redis中的数据
        for (String i:id
        ) {
            redisUtils.delete("Assort:"+ Base64Utils.encode(this.medicinalService.getById(this.drugService.getById(i).getMedicinalId()).getCategoryName()));
        }

        this.drugService.removeByIds(id);

        return new Result().ok();
    }

    @ApiOperation(value="药品管理，修改上架状态接口")
    @PostMapping("/drug/updateByid")
    public Result drugUpdateByid(@RequestBody Drug drug){
        //删除Redis中的数据
        redisUtils.delete("Assort:"+ Base64Utils.encode(this.medicinalService.getById(drug.getMedicinalId()).getCategoryName()));

        this.drugService.drugUpdateByid(drug);
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
    @ApiResponses({
            @ApiResponse( code = 200,message = "ok",response = Drug.class)
    })
    @GetMapping("/check/list")//查询的数据是待审核的
    public Result checkList(@RequestParam @ApiIgnore Map<String,Object> params){
        PageUtils list = this.drugService.list(params);
        return new Result().ok(list);
    }

    @ApiOperation(value="药品审核，查看接口")//可以直接调药品查看的接口
    @GetMapping("/check/select")
    public Result checkSelect(@RequestParam("id") String id){
        Drug drug = this.drugService.checkSelect(id);
        return new Result().ok(drug);
    }

    @ApiOperation(value="药品审核，审核接口")
    @PostMapping("/check/update")
    public Result checkUpdate(@RequestBody Drug drug){
        //删除Redis中的数据
        redisUtils.delete("Assort:"+ Base64Utils.encode(this.medicinalService.getById(drug.getMedicinalId()).getCategoryName()));

        System.out.println("_------------------------>"+drug);
        this.drugService.update(drug,new UpdateWrapper<Drug>().eq("`id`",drug.getId()));
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
    public Result checkTwoSymptomScreenSelect(@RequestParam("id") String id){
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
    public Result checkTwoMedicinalScreenSelect(@RequestParam("id") String id){
        List<Symptom> list = this.symptomService.list(new QueryWrapper<Symptom>().eq("higher_level", id));
        return new Result().ok(list);
    }

    @ApiOperation(value="药品审核，批量删除接口")
    @DeleteMapping("/check/deleteMore")
    public Result checkDeleteMore(@RequestBody List<String> id){
        //删除Redis中的数据
        for (String i:id
        ) {
            System.out.println(i+"--------------------------------++++++++");
            String medicinalId = this.drugService.getById(i).getMedicinalId();
            String categoryName = this.medicinalService.getById(medicinalId).getCategoryName();
            redisUtils.delete("Assort:"+ Base64Utils.encode(categoryName));
        }

        this.drugService.removeByIds(id);

        return new Result().ok();
    }

    @ApiOperation(value="药品审核，修改上架状态接口")
    @PostMapping("/check/updateByid")
    public Result checkUpdateByid(@RequestBody Drug drug){
        //删除Redis中的数据
        redisUtils.delete("Assort:"+ Base64Utils.encode(this.medicinalService.getById(drug.getMedicinalId()).getCategoryName()));

        this.drugService.updateById(drug);
        return new Result().ok();
    }
}
