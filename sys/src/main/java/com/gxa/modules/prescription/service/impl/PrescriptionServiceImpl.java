package com.gxa.modules.prescription.service.impl;

import com.gxa.modules.prescription.entity.Prescription;
import com.gxa.modules.prescription.mapper.PrescriptionMapper;
import com.gxa.modules.prescription.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionMapper prescriptionMapper;


    @Override
    public void addPrescription(Prescription prescription) {
        this.prescriptionMapper.addPrescription(prescription);

    }
}
