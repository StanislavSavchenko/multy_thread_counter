package com.test.task.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class CounterExecutorConfig {

    @Bean
    @Qualifier("supplierCounterExecutor")
    public ThreadPoolTaskExecutor getSupplierExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.initialize();
        executor.setThreadNamePrefix("SupplierThread");
        return executor;
    }

    @Bean
    @Qualifier("producerCounterExecutor")
    public ThreadPoolTaskExecutor getProducerCounterExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.initialize();
        executor.setThreadNamePrefix("ProducerThread");
        return executor;
    }

}
