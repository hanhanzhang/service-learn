package com.sdu.service.nacos.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "service.config")
public class ServiceConfigConfiguration {

    private String group;

}
