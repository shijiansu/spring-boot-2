package cn.timebusker;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

// implements AsyncConfigurer to customized processing.
@Configuration
@EnableAsync
@Slf4j
public class ArithmeticConfig implements AsyncConfigurer {

  private static final int MIN_POOL_SIZE = 5;
  private static final int MAX_POOL_SIZE = 20;
  private static final int QUEUE_CAPACITY = 10;

  @Override
  public Executor getAsyncExecutor() {
    // task executor by thread pool supported
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setCorePoolSize(MIN_POOL_SIZE);
    taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
    taskExecutor.setQueueCapacity(QUEUE_CAPACITY);
    taskExecutor.initialize();
    return taskExecutor;
  }

  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return (ex, method, params) -> {
      log.error("Exception message - {}", ex.getMessage());
      log.error("Method name - {}", method.getName());
      Arrays.stream(params).forEach(p -> log.error("Parameter value - {}", p));
    };
  }

  @Bean
  public Executor mineAsync() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(MIN_POOL_SIZE - 4); // min 1
    executor.setMaxPoolSize(MAX_POOL_SIZE - 10); // max 10
    executor.setQueueCapacity(QUEUE_CAPACITY - 5); // queue 5
    executor.setThreadNamePrefix("mineAsync-");
    // rejection-policy - use caller thread
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); //
    executor.initialize();
    return executor;
  }

}
