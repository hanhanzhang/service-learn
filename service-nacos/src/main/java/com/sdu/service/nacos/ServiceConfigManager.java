package com.sdu.service.nacos;

import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.sdu.service.nacos.configuration.NacosConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class ServiceConfigManager implements InitializingBean, INacosService {

    private ConfigService configService;

    @Autowired
    private NacosConfiguration cfg;

    @PostConstruct
    public void initialize() throws NacosException {
        Properties properties = from(cfg);
        configService = ConfigFactory.createConfigService(properties);
    }

    public String getConfig(String dataId, String group, long timeoutMs) throws NacosException {
        return configService.getConfig(dataId, group, timeoutMs);
    }

    public void registerConfigListener(String dataId, String group, Listener listener) throws NacosException {
        configService.addListener(dataId, group, listener);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("initial config: {}", getConfig("video_datahub_config", "video", 10000L));
        // for test
        registerConfigListener("video_datahub_config", "video", new Listener() {
            @Override
            public Executor getExecutor() {
                return Executors.newSingleThreadExecutor();
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                log.info("receive new config: {}", configInfo);
            }
        });
    }
}
