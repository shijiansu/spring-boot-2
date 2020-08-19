package spring.boot._2.jar.java13.docker.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application.class);
    // app.addListeners(new ApplicationPidFileWriter()); // pid file
    app.run(args);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
