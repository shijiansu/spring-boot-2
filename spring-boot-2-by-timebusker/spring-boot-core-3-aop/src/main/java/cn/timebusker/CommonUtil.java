package cn.timebusker;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonUtil {

  private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

  public static int add(int i) {
    log.info("CommonUtil：{}", ++i);
    return i;
  }

  public static String now() {
    return formatter.format(new Date());
  }
}
