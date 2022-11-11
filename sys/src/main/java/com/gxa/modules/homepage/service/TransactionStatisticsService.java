package com.gxa.modules.homepage.service;

import com.gxa.modules.homepage.entity.TransactionStatistics;

import java.util.Date;
import java.util.List;

public interface TransactionStatisticsService {
    List<TransactionStatistics> queryTransaction(Date today);
}
