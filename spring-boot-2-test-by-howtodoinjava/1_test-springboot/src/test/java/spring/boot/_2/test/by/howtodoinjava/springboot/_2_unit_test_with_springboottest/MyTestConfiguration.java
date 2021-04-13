package spring.boot._2.test.by.howtodoinjava.springboot._2_unit_test_with_springboottest;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import spring.boot._2.test.by.howtodoinjava.springboot.EmployeeHelper;

@TestConfiguration
public class MyTestConfiguration {

  @Bean // bean name is "helper"
  EmployeeHelper helper() {
    return new EmployeeHelper();
  }
}
