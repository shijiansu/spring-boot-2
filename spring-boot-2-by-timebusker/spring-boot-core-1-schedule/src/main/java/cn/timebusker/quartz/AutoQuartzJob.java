package cn.timebusker.quartz;

import static cn.timebusker.App.log;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

@Slf4j
public class AutoQuartzJob implements Job {

  public void execute(JobExecutionContext context) {
    log(getClass(), "quartz job");
  }
}