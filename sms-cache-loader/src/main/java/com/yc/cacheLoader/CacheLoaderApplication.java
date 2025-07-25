package com.yc.cacheLoader;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(basePackages = "com.yc.cacheLoader.mapper")
public class CacheLoaderApplication {
    public static void main(String[] args) {
        SpringApplication.run(CacheLoaderApplication.class, args);
    }
}
