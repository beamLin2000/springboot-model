package com.gxa.pay.service;


import com.gxa.pay.entity.DrugStockAndSalesVolume;

import java.util.List;

public interface DrugStockAndSalesVolumeService {
    List<DrugStockAndSalesVolume>drugList(String orderNo);
    void updateStockAndSalesVolume(Integer stock,Integer salesVolume,String drugId);
}
