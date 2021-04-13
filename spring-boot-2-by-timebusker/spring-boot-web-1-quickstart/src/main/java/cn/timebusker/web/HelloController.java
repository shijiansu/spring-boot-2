package cn.timebusker.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {

  @RequestMapping("/hello")
  public String hello() {
    log.info("Hello spring-boot-1-QuickStart!!!");
    return "Hello spring-boot-1-QuickStart!!!";
  }

}
