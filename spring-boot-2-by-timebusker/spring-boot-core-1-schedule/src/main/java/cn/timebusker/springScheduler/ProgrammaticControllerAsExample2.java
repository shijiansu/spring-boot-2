package cn.timebusker.springScheduler;

import static cn.timebusker.App.log;

import java.util.concurrent.ScheduledFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProgrammaticControllerAsExample2 {

  @Autowired
  private ThreadPoolTaskScheduler threadPoolTaskScheduler;

  private ScheduledFuture<?> future;

  @RequestMapping("/start")
  public String start() {
    String cron = "0/5 * * * * *";
    future = threadPoolTaskScheduler.schedule(new CustomizedTask(), new CronTrigger(cron));
    return cron;
  }

  @RequestMapping("/stop")
  public String stop() {
    if (future != null) {
      future.cancel(true);
    }
    return "stop";
  }

  @RequestMapping("/update")
  public String update() {
    String cron = "*/1 * * * * *";
    stop();
    future = threadPoolTaskScheduler.schedule(() -> log(getClass(), "programmatic task updated"),
        new CronTrigger(cron));
    return cron;
  }

  private static class CustomizedTask implements Runnable {

    @Override
    public void run() {
      log(getClass(), "programmatic task");
    }
  }
}
