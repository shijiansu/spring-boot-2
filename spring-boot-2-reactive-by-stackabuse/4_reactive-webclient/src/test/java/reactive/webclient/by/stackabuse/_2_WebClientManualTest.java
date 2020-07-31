package reactive.webclient.by.stackabuse;

import static java.time.Duration.between;
import static java.time.Instant.now;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Disabled("Disabled from mvn test because it is for manually test!")
public class _2_WebClientManualTest {
  static Logger logger = LoggerFactory.getLogger(_2_WebClientManualTest.class);

  static long elapsedTime(Instant start) {
    long elapsedTime = between(start, now()).toMillis();
    logger.info("Elapsed time: " + elapsedTime + "ms");
    return elapsedTime;
  }

  public String baseUrl() {
    return "http://localhost:8080";
  }

  @Test
  public void GIVEN_resttemplate_WHEN_issue_THEN_all_blocking() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(baseUrl()));
    Instant start = now();
    IntStream.rangeClosed(1, 5)
        .forEach(i -> restTemplate.getForObject("/person/{id}", Person.class, i));
    long elapsedTime = elapsedTime(start);
    assertTrue(elapsedTime > 10_000);
  }

  @Test
  public void GIVEN_webclient_WHEN_subscribe_each_THEN_fails_starting_request() {
    WebClient client = WebClient.create(baseUrl());
    Instant start = Instant.now();
    IntStream.rangeClosed(1, 5)
        .forEach(
            i ->
                client
                    .get()
                    .uri("/person/{id}", i)
                    .retrieve()
                    .bodyToMono(Person.class)
                    .subscribe()); // doesn't sit and wait for the response
    elapsedTime(start);
    // this code will end before issue the request to PersonController...
  }

  @Test
  public void GIVEN_webclient_WHEN_block_each_THEN_all_blocking() {
    WebClient client = WebClient.create(baseUrl());
    Instant start = Instant.now();
    IntStream.rangeClosed(1, 5)
        .forEach(
            i -> client.get().uri("/person/{id}", i).retrieve().bodyToMono(Person.class).block());
    long elapsedTime = elapsedTime(start);
    // works but blocking as using RestTemplate
    assertTrue(elapsedTime > 10_000);
  }

  @Test
  public void GIVEN_webclient_WHEN_mono_when_THEN_as_reactive() {
    WebClient client = WebClient.create(baseUrl());
    Instant start = Instant.now();

    List<Mono<Person>> list =
        Stream.of(1, 2, 3, 4, 5)
            .map(i -> client.get().uri("/person/{id}", i).retrieve().bodyToMono(Person.class))
            .collect(Collectors.toList());
    Mono.when(list) // aggregate given publishers into a new Mono that will be fulfilled when all of
        // the given Publishers have completed.
        .block(); // then block
    long elapsedTime = elapsedTime(start);
    assertTrue(elapsedTime < 5_000);
  }

  @Test
  public void GIVEN_webclient_WHEN_block_last_THEN_as_reactive() {
    WebClient client = WebClient.create(baseUrl());
    Instant start = Instant.now();

    Flux.range(1, 5)
        .flatMap(i -> client.get().uri("/person/{id}", i).retrieve().bodyToMono(Person.class))
        .blockLast(); // block indefinitely until the upstream signals its last value or completes.
    // Returns that value
    long elapsedTime = elapsedTime(start);
    assertTrue(elapsedTime < 5_000);
  }

  @Test
  public void GIVEN_webclient_WHEN_exchange_flatmap_THEN_as_reactive() {
    WebClient client = WebClient.create(baseUrl());
    Instant start = Instant.now();

    Flux.range(1, 5)
        .flatMap(
            i ->
                client
                    .get()
                    .uri("/person/{id}", i)
                    .exchange()
                    .flatMap(
                        response -> {
                          HttpStatus status = response.statusCode();
                          HttpHeaders headers = response.headers().asHttpHeaders();
                          logger.info("Got status = " + status + ", headers = " + headers);
                          return response.bodyToMono(Person.class);
                        }))
        .blockLast();
    long elapsedTime = elapsedTime(start);
    assertTrue(elapsedTime < 5_000);
  }

  @Test
  public void GIVEN_webclient_WHEN_2flatmap_THEN_as_reactive() {
    WebClient client = WebClient.create(baseUrl());
    Instant start = Instant.now();
    Flux.range(1, 5)
        .flatMap(
            i ->
                client
                    .get()
                    .uri("/person/{id}", i)
                    .retrieve()
                    .bodyToMono(Person.class)
                    .flatMap(
                        p ->
                            client
                                .get()
                                .uri("/person/{id}/hobby", p.getId())
                                .retrieve()
                                .bodyToMono(Hobby.class)))
        .blockLast();
    long elapsedTime = elapsedTime(start);
    assertTrue(elapsedTime < 10_000);
  }
}
