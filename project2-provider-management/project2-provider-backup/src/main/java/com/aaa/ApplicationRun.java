package com.aaa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @ClassName ApplicationRun
 * @Description TODO
 * @Author jyz
 * @date 2020/7/10 16:00
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.aaa.mapper")
public class ApplicationRun {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun.class,args);
    }
}
