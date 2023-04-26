package com.holden.wxproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName wxproject-ExecutorConfig
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2023年4月10日14:46 - 周一
 * @Describe
 */
@EnableAsync
@Configuration
public class ExecutorConfig {
    @Bean
    public static ScheduledExecutorService dataHandleThreadPool() {
        return new ScheduledThreadPoolExecutor(10, new UseThreadFactory("xh"));
    }
}
