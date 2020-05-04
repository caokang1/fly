package com.cignacmb.smart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

/**
 * Created by caokang on 19/12/31.
 */
@SpringBootApplication(scanBasePackages = {"com.cignacmb.smart.*"})
@EnableConfigurationProperties
public class SmartApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SmartApplication.class, args);
    }
}
