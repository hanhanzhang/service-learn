package com.sdu.service.nacos.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "service.instance")
public class ServiceInstanceConfiguration {

    private String serviceName;

    private float weight;

    private boolean enable;

    private boolean ephemeral;

    private String checkUrl;

    private String clusterName;

}
