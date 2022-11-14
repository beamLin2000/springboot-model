package com.gxa.modules.fristpage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("msg")
public class Msg {
    private Integer id;
    private String header;
    private String body;
}
