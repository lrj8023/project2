package com.aaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName ApplicationRun6081
 * @Description TODO
 * @Author jyz
 * @date 2020/7/16 18:22
 **/
@SpringBootApplication
@EnableEurekaServer
public class ApplicationRun6081 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun6081.class,args);
    }
}
