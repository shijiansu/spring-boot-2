package spring.boot._2.test.by.howtodoinjava.reactive.webflux;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@TestConfiguration
public class MyTestConfiguration {

  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
