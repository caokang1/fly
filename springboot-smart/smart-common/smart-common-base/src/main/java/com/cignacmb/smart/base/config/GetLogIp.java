package com.cignacmb.smart.base.config;

import ch.qos.logback.core.PropertyDefinerBase;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author: caokang
 * @Date: Created in 16:40 2020/4/17
 * @annotation:获取节点日志IP
 * @version:1.0
 */
public class GetLogIp extends PropertyDefinerBase {

    @Override
    public String getPropertyValue() {
        String ip = null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }
}
