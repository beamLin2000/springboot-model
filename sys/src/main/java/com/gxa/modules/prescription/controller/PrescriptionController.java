package com.gxa.modules.prescription.controller;

import com.gxa.common.utils.Result;
import com.gxa.modules.drugUserInfo.entity.DrugUserInfo;
import com.gxa.modules.prescription.entity.Prescription;
import com.gxa.modules.prescription.service.PrescriptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "处方")
@RestController
@Slf4j
public class PrescriptionController {


    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping("/prescription/add01")
    @ApiOperation("处方")
    public Result addPrescription(@RequestBody Prescription prescription){
        this.prescriptionService.addPrescription(prescription);
        return new Result().ok();
    }
}
