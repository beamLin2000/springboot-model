package com.gxa.modules.prescription.controller;

import com.gxa.common.utils.Result;
import com.gxa.modules.drugUserInfo.entity.DrugUserInfo;
import com.gxa.modules.prescription.entity.Prescription;
import com.gxa.modules.prescription.service.PrescriptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import javafx.beans.binding.StringBinding;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "购物车里面的添加处方")
@RestController
@Slf4j
public class PrescriptionController {


    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping("/prescription/add01")
    @ApiOperation("处方")
    @ApiImplicitParam(paramType = "query" ,name = "list",value = "病情",dataType = "list",required = true)
    public Result addPrescription(@RequestBody Prescription prescription,@RequestParam("list") List<String> list){
        StringBuilder stringBuilder = new StringBuilder();

        for (String string:list){
            stringBuilder.append(string);
            System.out.println("append"+stringBuilder);
        }

        String string = stringBuilder.toString();
        prescription.setDiagnosedDisease(string);
        System.out.println("list=========================="+list);
        this.prescriptionService.addPrescription(prescription);
        return new Result().ok(prescription);
    }
}
