package cn.timebusker.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LogsController {

  @RequestMapping(value = {"/", "/"}, method = RequestMethod.GET)
  public Long createLogs() {
    log.info("----------------------------------INFO");
    log.debug("----------------------------------DEBUG");
    log.error("----------------------------------ERROR");
    return System.currentTimeMillis();
  }
}
