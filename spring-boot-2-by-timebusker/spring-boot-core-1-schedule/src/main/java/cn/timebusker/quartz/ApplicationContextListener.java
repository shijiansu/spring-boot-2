package cn.timebusker.quartz;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Here use Servlet context to initiate scheduler
 */
@WebListener
@Slf4j
public class ApplicationContextListener implements ServletContextListener {

  private Scheduler scheduler = null;

  @Override
  @SneakyThrows
  public void contextInitialized(ServletContextEvent servletContextEvent) {
    // normally use StdSchedulerFactory to create scheduler.
    // Another option is DirectSchedulerFactory ,but with too much manual settings.
    scheduler = StdSchedulerFactory.getDefaultScheduler();
    scheduler.start();
    log.info("scheduler.start");
    log.info(scheduler.getSchedulerName());

    JobDetail jobDetail = JobBuilder.newJob(AutoQuartzJob.class)
        .withIdentity(AutoQuartzJob.class.getName(), "AutoQuartzGroup").build();
    // every 5 seconds
    SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
        .withIntervalInSeconds(5).repeatForever();
    Trigger trigger = TriggerBuilder.newTrigger()
        .withIdentity("trigger", "group")
        .withSchedule(simpleScheduleBuilder).startNow().build();

    scheduler.scheduleJob(jobDetail, trigger);
  }

  @Override
  @SneakyThrows
  public void contextDestroyed(ServletContextEvent arg0) {
    scheduler.shutdown();
  }

}
