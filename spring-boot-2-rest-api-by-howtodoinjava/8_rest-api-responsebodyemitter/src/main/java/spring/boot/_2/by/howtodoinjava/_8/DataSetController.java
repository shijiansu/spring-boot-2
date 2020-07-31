package spring.boot._2.by.howtodoinjava._8;

import static org.springframework.http.HttpStatus.OK;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@RestController
public class DataSetController {
  Logger l = LoggerFactory.getLogger(DataSetController.class);

  private final DataSetService dataSetService;

  public DataSetController(DataSetService dataSetService) {
    this.dataSetService = dataSetService;
  }

  @Autowired ObjectMapper objectMapper;

  @GetMapping("/fetch-data-sets-no-emitter")
  public ResponseEntity fetchNoEmitter() throws JsonProcessingException {
    List<DataSet> dataSets = dataSetService.findAll();
    StringBuilder sb = new StringBuilder();
    for (DataSet dataSet : dataSets) {
      sb.append(objectMapper.writeValueAsString(dataSet));
      randomDelay();
    }
    return new ResponseEntity<>(sb.toString(), OK);
  }

  @GetMapping("/fetch-data-sets")
  public ResponseBodyEmitter fetch() {
    ResponseBodyEmitter emitter = new ResponseBodyEmitter();

    // Every request here to create a seperated thread to handle
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
