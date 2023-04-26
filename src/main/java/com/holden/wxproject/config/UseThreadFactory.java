package com.holden.wxproject.config;

import javax.validation.constraints.NotNull;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

/**
 * @ClassName wxproject-UseThreadFactory
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2023年4月10日15:06 - 周一
 * @Describe
 */
public class UseThreadFactory implements ThreadFactory {
    private final String name;

    public UseThreadFactory(String name) {
        this.name = name;
    }

    @Override
    public Thread newThread(@NotNull Runnable run) {
        return new Thread(run, "MyThread-" + name);
    }
}

