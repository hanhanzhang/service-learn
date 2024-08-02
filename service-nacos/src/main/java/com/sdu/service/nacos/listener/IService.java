package com.sdu.service.nacos.listener;

import com.alibaba.nacos.api.naming.pojo.Instance;
import com.google.common.base.Preconditions;
import com.sdu.service.nacos.configuration.ServiceInstanceConfiguration;
import com.sdu.service.nacos.utils.NetUtils;
import io.grpc.netty.shaded.io.netty.util.NetUtil;
import org.springframework.core.env.Environment;

public interface IService {

    default Instance buildInstance(Environment env, ServiceInstanceConfiguration instanceCfg) {
        Instance instance = new Instance();

        instance.setIp(NetUtils.getServerIp());
        instance.setPort(serviceInstancePort(env));
        instance.setEnabled(instance.isEnabled());
        instance.setEphemeral(instanceCfg.isEphemeral());
        instance.setWeight(instanceCfg.getWeight());
        instance.setHealthy(instanceCfg.getCheckUrl() != null && !instanceCfg.getCheckUrl().isEmpty());
        instance.setServiceName(instanceCfg.getServiceName());
        instance.setClusterName(instanceCfg.getClusterName());

        return instance;
    }


    static Integer serviceInstancePort(Environment env) {
        Preconditions.checkArgument(env != null);
        return env.getProperty("server.port", Integer.class);
    }
}
