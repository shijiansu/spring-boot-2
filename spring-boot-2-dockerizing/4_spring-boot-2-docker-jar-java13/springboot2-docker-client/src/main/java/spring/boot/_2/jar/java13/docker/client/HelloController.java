package spring.boot._2.jar.java13.docker.client;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {
  Logger log = LoggerFactory.getLogger(HelloController.class);

  @Autowired RestTemplate restTemplate;

  // adapt to non-docker and docker env, docker env uses hostname to connect
  @Value("${microservice.endpoint:localhost}")
  String endpoint;

  @PostConstruct
  public void env() {
    log.info("********** Service endpoint: {}", endpoint);
  }

  @RequestMapping("/")
  public String hello() {
    return "Hello Docker Client World";
  }

  @RequestMapping("/service")
  public String service() {
    ResponseEntity<String> result =
        restTemplate.getForEntity("http://" + endpoint + ":8080/", String.class);

    return result.getBody();
  }
}
