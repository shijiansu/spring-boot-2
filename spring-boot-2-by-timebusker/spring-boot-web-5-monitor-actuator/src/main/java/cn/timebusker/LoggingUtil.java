package cn.timebusker;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingUtil {

  public static void creatLogging() {
    log.info("----------------------------------INFO");
    log.debug("----------------------------------DEBUG");
    log.error("----------------------------------ERROR");
  }
}
