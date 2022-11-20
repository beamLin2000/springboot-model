package com.gxa.modules.homepage.service.impl;

import com.gxa.modules.homepage.entity.TransactionStatistics;
import com.gxa.modules.homepage.mapper.TransactionStatisticsMapper;
import com.gxa.modules.homepage.service.TransactionStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class TransactionStatisticsServiceImpl implements TransactionStatisticsService {
    @Autowired
    private TransactionStatisticsMapper transactionStatisticsMapper;
    @Override
    public List<TransactionStatistics> queryTransaction(Date today) {
        List<TransactionStatistics> list = new ArrayList<>();
        long time1 = 0;
        for (int i=0;i<7;i++){
            Date tomorrow = new Date();
            long time = today.getTime();
            time1= time-24*60*60*1000;
            today.setTime(time1);
            tomorrow.setTime(time);
            System.out.println(today);
            Double aDouble = this.transactionStatisticsMapper.queryTransaction(today, tomorrow);
            if (aDouble==null){
                aDouble=0.0;
            }
            TransactionStatistics transactionStatistics = new TransactionStatistics(new Date(tomorrow.getTime()-24*60*60*1000),aDouble);
            list.add(transactionStatistics);
        }
        return list;
    }
}
