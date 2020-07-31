package spring.boot._2.by.howtodoinjava._9;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class DataSetController {
  Logger l = LoggerFactory.getLogger(DataSetController.class);

  private final DataSetService dataSetService;

  public DataSetController(DataSetService dataSetService) {
    this.dataSetService = dataSetService;
  }

  // will send the response to browser 1 by 1 to download the content
  @GetMapping("/emit-data-sets")
  public SseEmitter fetch() {
    SseEmitter emitter = new SseEmitter();
    ExecutorService executor = Executors.newSingleThreadExecutor();
    executor.execute(
        () -> {
          List<DataSet> dataSets = dataSetService.findAll();
          for (DataSet dataSet : dataSets) {
            try {
              emitter.send(dataSet, MediaType.APPLICATION_JSON);
              randomDelay();
            } catch (IOException e) {
              emitter.completeWithError(e);
              return;
            }
          }
          emitter.complete();
        });
    executor.shutdown();
    return emitter;
  }

  private void randomDelay() {
    try {
      int sleeping = new Random().nextInt(5_000);
      l.info("sleeping... " + sleeping + "ms");
      Thread.sleep(sleeping);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
