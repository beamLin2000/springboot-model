package com.gxa.modules.prescription.mapper;

import com.gxa.modules.prescription.entity.Prescription;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrescriptionMapper {
    void addPrescription(Prescription prescription);
}
