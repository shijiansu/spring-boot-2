package cn.timebusker;

import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ArithmeticController {

  @Autowired
  private ArithmeticService arithmeticService;

  @RequestMapping(value = {"/"}, method = RequestMethod.GET)
  public void index() throws Exception {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    log.info("--------------------------------------------");
    Future<Long> task1 = arithmeticService.subByAsync();
    arithmeticService.processInAsyncAndReturnVoid();

    // blocking here, but task 1 and task 2 are running anyc
    long sync = arithmeticService.processInSync();
    log.info("........task 3 - sync - processing time: {} ms", sync);

    while (true) {
      if (task1.isDone()) {
        long async = task1.get();
        log.info("........task 1 - async - processing time: {} ms", async);
        break;
      }
    }
    log.info("--------------------------------------------");

    stopWatch.stop();
    // this time is very closed to 5000 ms, so only the snyc task blocking the thread
    log.info("........response time: {} ms", stopWatch.getTotalTimeMillis());
  }

  /**
   * 自定义实现线程异步
   */
  @RequestMapping(value = {"/mine", "/m*"}, method = RequestMethod.GET)
  public void mineAsync() throws Exception {
    // describe the process,
    // the pool for executor is 10, while exceeded,
    // it use the caller thread, which is the name as below.
    log.info("........start mineAsync for loop, {}", Thread.currentThread().getName());
    for (int i = 0; i < 50; i++) {
      arithmeticService.mineInAsync(i);
    }
    // once mineInAsync received request then it moves forward to below print
    log.info("........completed mineAsync for loop");
  }
}
