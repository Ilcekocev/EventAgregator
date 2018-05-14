package com.sorsix.eventagregator.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;

@EnableAsync
@EnableScheduling
@Configuration
public class AsyncConfiguration implements SchedulingConfigurer, AsyncConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(AsyncConfiguration.class);

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        TaskScheduler taskScheduler = this.taskScheduler();
        logger.info("Finished configuring scheduled method executor {}", taskScheduler);
    }

    @Override
    public Executor getAsyncExecutor() {
       Executor executor = this.taskScheduler();
       logger.info("Finished configuring async method executor");
       return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }

    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(30);
        taskScheduler.setThreadNamePrefix("email-sending-task-");
        taskScheduler.setAwaitTerminationSeconds(60);
        taskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        taskScheduler.setErrorHandler(task -> logger.error("Error occured for task: {}", task));
        taskScheduler.setRejectedExecutionHandler(((runnable, threadPoolExecutor) -> logger.error("Execution for the task {}, has been rejected", runnable)));
        return taskScheduler;
    }
}
