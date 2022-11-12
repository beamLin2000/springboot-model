package com.gxa;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class SpringbootProjectModelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootProjectModelApplication.class, args);
    }

}
