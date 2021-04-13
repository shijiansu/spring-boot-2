package cn.timebusker.springScheduler;

import static cn.timebusker.App.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

/**
 * Additional example to show if there is a requirement to control the scheduler setting in
 * details.
 */
@Configuration
@EnableScheduling
@Slf4j
public class DynamicAsExample3 implements SchedulingConfigurer {

  // able to update the cron value in the program and it will take effect in code
  private String cron;

  public DynamicAsExample3() {
    cron = "0/5 * * * * *";
    new Thread(() -> {
      try {
        Thread.sleep(30000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      cron = "0/1 * * * * *";
    }).start();
  }

  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    Runnable task = () -> log(getClass(), "dynamic task");

    Trigger trigger = triggerContext -> {
      CronTrigger cronTrigger = new CronTrigger(cron);
      return cronTrigger.nextExecutionTime(triggerContext); // next execution time updated
    };
    taskRegistrar.addTriggerTask(task, trigger);
  }
}
