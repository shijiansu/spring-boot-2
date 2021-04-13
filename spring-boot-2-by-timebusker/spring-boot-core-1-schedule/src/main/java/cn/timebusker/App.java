package cn.timebusker;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ServletComponentScan // to support the following Servlet 3.0 annotations
@Slf4j
public class App implements CommandLineRunner {

  public static void log(Class clazz, String message) {
    log.info("{} : {} : {}", new Date(), clazz, message);
  }

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    RestTemplate template = new RestTemplate();
    String response = template.getForObject("http://localhost:8080/start", String.class);
    log.info("response: {}", response);
    TimeUnit.SECONDS.sleep(5);

    response = template.getForObject("http://localhost:8080/update", String.class);
    log.info("response: {}", response);
    TimeUnit.SECONDS.sleep(5);

    response = template.getForObject("http://localhost:8080/stop", String.class);
    log.info("response: {}", response);
  }
}
