package com.sdu.service.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class NacosBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(NacosBootstrap.class, args);
    }

}