package com.cignacmb.smart.base.utils.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableScheduling
public class QuartzJobConfig {

    @Autowired
    private SmartJobFactory smartJobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        factory.setStartupDelay(8);
        factory.setWaitForJobsToCompleteOnShutdown(false);

        Properties p = quartzProperties();

        if(p != null) {
            factory.setQuartzProperties(p);
        }

        factory.setJobFactory(smartJobFactory);
        return factory;
    }

    @Bean
    public Properties quartzProperties() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("resources/config/quartz.properties"));

        try {
            propertiesFactoryBean.afterPropertiesSet();

            return propertiesFactoryBean.getObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
