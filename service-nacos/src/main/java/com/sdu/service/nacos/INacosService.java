package com.sdu.service.nacos;

import com.alibaba.nacos.api.PropertyKeyConst;
import com.sdu.service.nacos.configuration.NacosConfiguration;

import java.util.Properties;

public interface INacosService {

    default Properties from(NacosConfiguration cfg) {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.NAMESPACE, cfg.getNamespace());
        properties.setProperty(PropertyKeyConst.SERVER_ADDR, cfg.getServerAddress());
        properties.setProperty(PropertyKeyConst.USERNAME, cfg.getUserName());
        properties.setProperty(PropertyKeyConst.PASSWORD, cfg.getUserPassword());
        return properties;
    }

}
