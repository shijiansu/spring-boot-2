package cn.timebusker.springScheduler;

import static cn.timebusker.App.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableScheduling
@Slf4j
public class ConfigAsExample1 {

  /**
   * 每10秒打印一次:时间取决于运行系统时间
   */
  @Scheduled(cron = "0/10 * * * * *")
  public void fixedTask1() {
    log(getClass(), "fixed task1");
  }

  /**
   * 每1分钟打印一次:时间取决于运行系统时间
   */
  @Scheduled(cron = "0 0/1 * * * *")
  public void fixedTask2() {
    log(getClass(), "fixed task2");
  }

  @Bean
  public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
    return new ThreadPoolTaskScheduler();
  }
}
