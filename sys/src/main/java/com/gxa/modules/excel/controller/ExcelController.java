package com.gxa.modules.excel.controller;


import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.extension.api.R;
import com.gxa.common.utils.Result;
import com.gxa.modules.excel.utils.EasyPoiUtils;
import com.gxa.modules.sys.entity.User;
import com.gxa.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExcelController {

    @Autowired
    private UserService service;
    @GetMapping("/exportToExcel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        List<User> list = this.service.queryUser();
        EasyPoiUtils.exportExcelByType(list,"导出文件","123",User.class,"aaa.xlsx",response, ExcelType.XSSF);

    }
    @PostMapping("/impportToExcel")
    public Result impportToExcel(@RequestParam("file") MultipartFile file) throws IOException {
        List<User> list = EasyPoiUtils.importExcel(file, User.class);
        System.out.println(list);
        return new Result().ok();
    }
}
