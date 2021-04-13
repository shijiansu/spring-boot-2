package cn.timebusker.aop;

import static cn.timebusker.CommonUtil.now;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect // Declaring an Aspect
@Order(2) // less is with higher order
@Slf4j
@Profile("test3") // only enable in CommonControllerTest3
public class LoggerHandler2Aop {

  @Pointcut(value = "execution(* cn.timebusker.service.*.*(..))")
  public void executionP1() {
  }

  @Around(value = "executionP1()")
  public Object around(ProceedingJoinPoint pjp) {
    log.info("############ LoggerHandler2Aop.aroundAdvice...time: {}", now());
    Object obj = null;
    try {
      Object[] args = pjp.getArgs();
      log.info("############ Aspect around 1: {}", Arrays.asList(args));
      obj = pjp.proceed();
      log.info("############ Aspect around 2: {}", obj);
    } catch (Throwable e) {
      e.printStackTrace();
    }
    return obj;
  }
}
