package com.xdsty.retrytask.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolExecutorConfig {

    @Value("${retry.thread.pool.core}")
    private Integer corePoolSize;

    @Bean
    @Qualifier("retryTaskThreadPool")
    public ThreadPoolExecutor retryTaskThreadPool() {
        return new ScheduledThreadPoolExecutor(corePoolSize);
    }

}
