package com.sdu.service.nacos.listener;

import com.alibaba.nacos.api.exception.NacosException;
import com.sdu.service.nacos.ServiceRegisterManager;
import com.sdu.service.nacos.configuration.ServiceInstanceConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ServiceUnregisterListener implements ApplicationListener<ContextClosedEvent>, IService {

    @Autowired
    private ServiceRegisterManager serviceRegisterManager;

    @Autowired
    private ServiceInstanceConfiguration instanceCfg;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        try {
            Environment env = event.getApplicationContext().getEnvironment();
            serviceRegisterManager.unregisterServiceInstance(buildInstance(env, instanceCfg));
            log.info("deregister service instance success.");
        } catch (NacosException e) {
            log.error("failed deregister service instance", e);
            throw new RuntimeException(e);
        }
    }

}
