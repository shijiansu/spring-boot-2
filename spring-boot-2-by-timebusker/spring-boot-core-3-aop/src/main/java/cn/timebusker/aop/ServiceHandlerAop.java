package cn.timebusker.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
@Order(10)
@Slf4j
public class ServiceHandlerAop {

  ThreadLocal<StopWatch> startTime;

  @Pointcut("within(*..service..*)") // pointcut as configuration, not handler
  public void service() {
  }

  @Before("service()") // before above service() method
  public void before() {
    startTime = new ThreadLocal<>();
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    startTime.set(stopWatch);
  }

  @After("service()") // before above service() method
  public void after() {
    StopWatch stopWatch = startTime.get();
    if (stopWatch != null) {
      stopWatch.stop();
      log.info("ServiceHandlerAop.service...time: {}ms", stopWatch.getTotalTimeMillis());
    }
  }
}
