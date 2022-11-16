package com.gxa.pay.service.impl;
import com.gxa.pay.entity.DrugStockAndSalesVolume;
import com.gxa.pay.mapper.DrugStockAndSalesVolumeMapper;
import com.gxa.pay.service.DrugStockAndSalesVolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugStockAndSalesVolumeServiceImpl implements DrugStockAndSalesVolumeService {

    @Autowired
    private DrugStockAndSalesVolumeMapper drugStockAndSalesVolumeMapper;

    @Override
    public List<DrugStockAndSalesVolume> drugList(String orderNo) {
        List<DrugStockAndSalesVolume> list = this.drugStockAndSalesVolumeMapper.list(orderNo);
        return list;
    }

    @Override
    public void updateStockAndSalesVolume(Integer stock, Integer salesVolume,String drugId) {
        this.drugStockAndSalesVolumeMapper.updateStockAndSalesVolume(stock,salesVolume,drugId);
    }
}
