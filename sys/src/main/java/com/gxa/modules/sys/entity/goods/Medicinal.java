package com.gxa.modules.sys.entity.goods;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("medicinal")
public class Medicinal {


    private int id;//id

    private String categoryName;//分类名称

    private String rank;//级别

    private String state;//状态

    private String remarks;//备注

    private String uploader;//上传人

    private Date addTime;//添加时间


}
