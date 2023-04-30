package com.ymy.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class CompanyApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompanyApplication.class, args);
    }

}
