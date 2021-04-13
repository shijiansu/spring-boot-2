package reactive.by.liukang._2_webflux._3_reactive_data;

import static java.time.Duration.ofSeconds;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON_VALUE;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired private UserService userService;

  @PostMapping(value = "/", consumes = APPLICATION_JSON_VALUE)
  public Mono<User> save(@RequestBody User user) {
    log.info("User: {}", user);
    log.info("User: {}", user.getName());
    log.info("User: {}", user.getPhone());
    log.info("User: {}", user.getUsername());
    log.info("User: {}", user.getBirthday());
    return userService.save(user);
  }

  @DeleteMapping("/{username}")
  public Mono<Long> deleteByUsername(@PathVariable String username) {
    return userService.deleteByUsername(username);
  }

  @GetMapping("/{username}")
  public Mono<User> findByUsername(@PathVariable String username) {
    return userService.findByUsername(username);
  }

  @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
  public Flux<User> findAll() {
    return userService
        .findAll()
        .log() // more detailed log
        .delayElements(ofSeconds(1));
  }

  @GetMapping(value = "/sse", produces = APPLICATION_STREAM_JSON_VALUE)
  public Flux<User> findAllSse() {
    return userService.findAll().delayElements(ofSeconds(1));
  }
}
