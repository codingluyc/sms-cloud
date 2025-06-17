package com.yc.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CacheStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheStarterApplication.class, args);
    }
}
