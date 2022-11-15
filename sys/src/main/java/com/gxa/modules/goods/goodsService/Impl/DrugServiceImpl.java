package com.gxa.modules.goods.goodsService.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Query;
import com.gxa.modules.goods.goodsEntity.Drug;
import com.gxa.modules.goods.goodsEntity.Medicinal;
import com.gxa.modules.goods.goodsEntity.Symptom;
import com.gxa.modules.goods.goodsMapper.DrugGoodsMapper;
import com.gxa.modules.goods.goodsService.DrugService;
import com.gxa.modules.goods.goodsService.MedicinalService;
import com.gxa.modules.goods.goodsService.SymptomService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DrugServiceImpl extends ServiceImpl<DrugGoodsMapper, Drug> implements DrugService {
    @Autowired
    private MedicinalService medicinalService;
    @Autowired
    private SymptomService symptomService;

    @Override
    public PageUtils list(Map<String, Object> params) {
        IPage<Drug> page = this.page(new Query<Drug>().getPage(params),
                new QueryWrapper<Drug>().like(StringUtils.isNotEmpty((String) params.get("drugName")),"`drug_name`",params.get("drugName"))
                .eq(StringUtils.isNotEmpty((String) params.get("medicinalId")),"medicinal_id",params.get("medicinalId"))
                .eq(StringUtils.isNotEmpty((String) params.get("symptomId")),"symptom_id",params.get("symptomId"))
                .eq(StringUtils.isNotEmpty((String) params.get("shelves")),"`shelves`",params.get("shelves"))
                .eq(StringUtils.isNotEmpty((String) params.get("state")),"`state`",params.get("state"))
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils listCheck(Map<String, Object> params) {
        IPage<Drug> page = this.page(new Query<Drug>().getPage(params),
                new QueryWrapper<Drug>().like(StringUtils.isNotEmpty((String) params.get("drugName")),"`drug_name`",params.get("drugName"))
                        .eq(StringUtils.isNotEmpty((String) params.get("medicinalId")),"medicinal_id",params.get("medicinalId"))
                        .eq(StringUtils.isNotEmpty((String) params.get("symptomId")),"symptom_id",params.get("symptomId"))
                        .eq("state","待审核")
        );

        return new PageUtils(page);
    }

    public Drug drugSelect(String id){
        Drug drug = this.baseMapper.selectById(id);

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

        return drug;
    }

    public void drugUpdateByid(Drug drug){
        if(drug.getShelves().equals("0")){
            drug.setShelves("1");
            this.baseMapper.update(drug,new UpdateWrapper<Drug>().eq("id",drug.getId()));
        }else {
            drug.setShelves("0");
            this.baseMapper.update(drug,new UpdateWrapper<Drug>().eq("id",drug.getId()));
        }
    }

    public Drug checkSelect(String id){
        Drug drug = this.baseMapper.selectById(id);

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

        return drug;
    }
}
