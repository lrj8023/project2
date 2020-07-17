package com.aaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName ApplicationRun6083
 * @Description TODO
 * @Author jyz
 * @date 2020/7/16 18:40
 **/
@SpringBootApplication
@EnableEurekaServer
public class ApplicationRun6083 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun6083.class,args);
    }
}
