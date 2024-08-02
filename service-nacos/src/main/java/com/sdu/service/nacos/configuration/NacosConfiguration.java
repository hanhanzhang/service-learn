package com.sdu.service.nacos.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "nacos")
public class NacosConfiguration {

    private String serverAddress;

    private String namespace;

    private String group;

    private String userName;

    private String userPassword;

}
