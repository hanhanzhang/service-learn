package com.sdu.service.nacos.utils;

import java.net.InetAddress;

public class NetUtils {

    private NetUtils() { }

    public static String getServerIp() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            return inetAddress.getHostAddress();
        } catch (Exception e) {
            throw new RuntimeException("failed got server ip", e);
        }
    }

}
