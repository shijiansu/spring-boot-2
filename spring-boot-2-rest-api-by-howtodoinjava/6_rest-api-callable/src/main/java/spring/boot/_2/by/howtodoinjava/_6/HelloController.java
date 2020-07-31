package spring.boot._2.by.howtodoinjava._6;

import static java.util.concurrent.ThreadLocalRandom.current;
import static org.springframework.http.HttpStatus.OK;

import java.util.concurrent.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
  Logger l = LoggerFactory.getLogger(HelloController.class);

  @GetMapping(value = "/hello")
  public Callable<String> hello() {
    return () -> {
      l.info("hello");
      Thread.sleep(current().nextInt(5_000));
      return "Hello World !!";
    };
  }

  @GetMapping(value = "/goodbye")
  public ResponseEntity<String> goodbye() throws InterruptedException {
    l.info("goodbye");
    Thread.sleep(current().nextInt(5_000));
    return new ResponseEntity<>("Good Bye !!", OK);
  }
}
