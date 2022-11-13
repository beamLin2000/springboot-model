package com.gxa.modules.homepage.service;

import java.util.Date;
import java.util.Map;

public interface PandentService {
    Map queryCommodityAndUserPandent(Date today,Date yesterday,Date tomorrow,Date firstday,Date lastday);
}
