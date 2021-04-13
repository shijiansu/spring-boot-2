package cn.timebusker.aop;

import org.aspectj.lang.annotation.Pointcut;

public class LoggerHandlerAopExamples {

  /**
   * execution - by execution package, return type, class, method, input parameters. Only for class
   * but not interface.
   */
  // cn.timebusker.service and all sub packages
  @Pointcut(value = "execution(* cn.timebusker.service.*.*(..))")
  public void executionP1() {
  }

  // cn.timebusker.service and direct sub package (only 1 level)
  @Pointcut(value = "execution(* *..service.*.*(..))")
  public void executionP2() {
  }

  // service and all sub packages
  @Pointcut(value = "execution(* *..service..*(..))")
  public void executionP3() {
  }

  // return type = java.lang.String and service and all sub packages
  @Pointcut(value = "execution(java.lang.String *..service..*(..))")
  public void executionP4() {
  }

  // return type = int and service and all sub packages
  @Pointcut(value = "execution(int *..service..*(..))")
  public void executionP5() {
  }

  // cn.timebusker and sub packages - method start at "add" and input parameter is String
  @Pointcut(value = "execution(* cn.timebusker..add*(String))")
  public void executionP6() {
  }

  @Pointcut(value = "execution(* cn.timebusker.service.*.add*(int))")
  public void executionP7() {
  }

  // can use with &&, || and !
  @Pointcut(value = "execution(* cn.timebusker.service.*.add*(int)) || execution(* cn.timebusker..add*(String))")
  public void executionP8() {
  }

  /**
   * within - by package and class
   */
  // methods in class
  @Pointcut(value = "within(cn.timebusker.service.OrderServiceImpl)")
  public void withinP1() {
  }

  // package
  @Pointcut(value = "within(cn.timebusker.service.order.Impl.*)")
  public void withinP2() {
  }

  /**
   * this - proxied class (only single interface of proxy, or )
   */
  // 匹配指定类型内的方法执行(包下所有的类)
  @Pointcut(value = "this(cn.timebusker.service.OrderServiceImpl)")
  public void thisP1() {
  }

  /**
   * target - target proxied class of proxy (interface)
   */
  @Pointcut(value = "target(cn.timebusker.service.OrderService)")
  public void targetP1() {
  }

  // args - costly, better do not use

  /**
   * annotation - only for annotation in the class
   */
  @Pointcut(value = "@annotation(org.springframework.stereotype.Service)")
  public void annotationP1() {
  }

  @Pointcut(value = "@within(cn.timebusker.annotation.TimebuskerBean)")
  public void annotationP2() {
  }

  @Pointcut(value = "@annotation(cn.timebusker.annotation.TimebuskerMethod)")
  public void annotationP3() {
  }

}
