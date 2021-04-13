package reactive.by.liukang._2_webflux._1_reactive_web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {

  @GetMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
  public Mono<String> hello() {
    return Mono.just("Welcome to reactive world ~");
  }
}
