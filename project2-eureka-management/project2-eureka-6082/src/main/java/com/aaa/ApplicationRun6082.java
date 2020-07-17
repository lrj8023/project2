package com.aaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName ApplicationRun6082
 * @Description TODO
 * @Author jyz
 * @date 2020/7/16 18:36
 **/
@SpringBootApplication
@EnableEurekaServer
public class ApplicationRun6082 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun6082.class,args);
    }
}
