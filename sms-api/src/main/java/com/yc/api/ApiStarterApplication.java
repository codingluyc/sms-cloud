package com.yc.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiStarterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiStarterApplication.class, args);
    }
}
