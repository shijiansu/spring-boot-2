package cn.timebusker;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Slf4j
public class ArithmeticService {

  public static final int COUNT = 5000;

  @Async
  public Future<Long> subByAsync() throws Exception {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    TimeUnit.MILLISECONDS.sleep(COUNT);
    stopWatch.stop();
    log.info("Completed task 1");
    return new AsyncResult<>(stopWatch.getTotalTimeMillis());
  }

  @Async
  public void processInAsyncAndReturnVoid() throws Exception {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    TimeUnit.MILLISECONDS.sleep(COUNT);
    stopWatch.stop();
    log.info("Completed task 2");
    log.info("........task 2 - async - processing time: {} ms", stopWatch.getTotalTimeMillis());
  }

  public long processInSync() throws Exception {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    // Thread.sleep(COUNT);
    TimeUnit.MILLISECONDS.sleep(COUNT);
    stopWatch.stop();
    log.info("Completed task 3");
    return stopWatch.getTotalTimeMillis();
  }

  @Async("mineAsync")
  public void mineInAsync(int i) throws Exception {
    TimeUnit.SECONDS.sleep(5);
    log.info("{}", i);
  }
}
