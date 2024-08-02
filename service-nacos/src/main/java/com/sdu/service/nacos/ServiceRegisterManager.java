package com.sdu.service.nacos;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.google.common.base.Preconditions;
import com.sdu.service.nacos.configuration.NacosConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Slf4j
@Component
public class ServiceRegisterManager implements INacosService {

    private NamingService namingService;

    @Autowired
    private NacosConfiguration cfg;

    @PostConstruct
    public void initialize() throws NacosException {
        Properties properties = from(cfg);
        namingService = NamingFactory.createNamingService(properties);
    }

    public void registerServerInstance(Instance instance) throws NacosException {
        Preconditions.checkArgument(instance != null);
        namingService.registerInstance(instance.getServiceName(), cfg.getGroup(), instance);
    }

    public void unregisterServiceInstance(Instance instance) throws NacosException {
        Preconditions.checkArgument(instance != null);
        namingService.deregisterInstance(instance.getServiceName(), cfg.getGroup(), instance);
    }

}
