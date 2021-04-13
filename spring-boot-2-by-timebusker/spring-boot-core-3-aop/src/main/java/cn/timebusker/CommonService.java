package cn.timebusker;

import cn.timebusker.annotation.TimebuskerBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@TimebuskerBean
@Slf4j
public class CommonService {

  public int add(int i) {
    log.info("CommonService： {}", ++i);
    return i;
  }
}
	