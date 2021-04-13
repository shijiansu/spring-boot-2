package cn.timebusker.aop;

import static cn.timebusker.CommonUtil.now;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect // Declaring an Aspect
@Order(1) // less is with higher order
@Slf4j
@Profile("test2") // only enable in CommonControllerTest2
public class LoggerHandlerAop {

  @Pointcut(value = "execution(* cn.timebusker.service.*.*(..))")
  public void executionP1() {
  }

  @Before("executionP1()") // @Before("log6() OR log7()")
  public void before(JoinPoint jp) {
    log.info("logHandlerAop.beforeAdvice...time: {}", now());
    Signature signature = jp.getSignature();
    log.info("declaring type name: {}", signature.getDeclaringTypeName());
    log.info("declaring type: {}", signature.getDeclaringType());

    log.info("proxyClass: {}", signature.getClass());
    log.info("method: {}", signature.getName());
    log.info("parameter: {}", JSON.toJSONString(jp.getArgs()));
    log.info("target: {}", jp.getTarget());
    log.info("object this: {}", jp.getThis());
  }

  @AfterReturning("executionP1()")
   public void afterReturn() {
    log.info("logHandlerAop.afterReturningAdvice...time: {}", now());
  }

  @AfterThrowing("executionP1()")
  public void afterThrowing() {
    log.info("logHandlerAop.afterThrowingAdvice...time: {}", now());
  }

  @After("executionP1()")
  public void after() {
    log.info("logHandlerAop.afterFinallyAdvice...time: {}", now());
  }

}
