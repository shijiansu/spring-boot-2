package reactive.by.liukang._2_webflux;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactive.by.liukang._2_webflux._1_reactive_web.HelloController;
import reactive.by.liukang._2_webflux._3_reactive_data.User;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWebTestClient
public class WebfluxApplicationTest {

  @LocalServerPort int port;

  @Autowired WebTestClient client;

  @Autowired HelloController controller;

  @Test
  public void contextLoads() {
    assertNotNull(controller);
  }

  @Test
  public void hello() {
    client
        .get()
        .uri("/hello")
        .exchange()
        .expectStatus()
        .isOk()
        .expectHeader()
        .contentTypeCompatibleWith(MediaType.TEXT_PLAIN)
        .expectBody(String.class)
        .isEqualTo("Welcome to reactive world ~");
  }

  @Test
  public void date() {
    client
        .get()
        .uri("/date")
        .exchange()
        .expectStatus()
        .isOk()
        .expectHeader()
        .contentTypeCompatibleWith(MediaType.TEXT_PLAIN)
        .expectBody(String.class);
  }

  @Test
  public void time() {
    client
        .get()
        .uri("/time")
        .exchange()
        .expectStatus()
        .isOk()
        .expectHeader()
        .contentTypeCompatibleWith(MediaType.TEXT_PLAIN)
        .expectBody(String.class);
  }

  @Test
  public void hello_webclient() throws InterruptedException {
    WebClient webClient = WebClient.create("http://localhost:" + port);
    Mono<String> resp = webClient.get().uri("/hello").retrieve().bodyToMono(String.class);
    resp.subscribe(System.out::println);
    TimeUnit.SECONDS.sleep(1L);
  }

  @Test
  public void user_webclient() {
    WebClient webClient = WebClient.builder().baseUrl("http://localhost:" + port).build();
    webClient
        .post()
        .uri("/user/")
        .contentType(APPLICATION_JSON)
        .accept(APPLICATION_JSON)
        .body(Mono.just(new User(null, "wangwu", "Wang Wu", "99999999", new Date())), User.class)
        .exchange()
        .block();

    User user =
        webClient
            .get()
            .uri("/user/wangwu")
            .accept(APPLICATION_JSON)
            .exchange()
            .flatMapMany(response -> response.bodyToFlux(User.class))
            .doOnNext(System.out::println)
            .blockLast();
    assertNotNull(user);
    assertEquals("wangwu", user.getUsername());
  }

  @Test
  public void times_webclient() {
    WebClient webClient = WebClient.create("http://localhost:" + port);
    webClient
        .get()
        .uri("/times")
        .accept(TEXT_EVENT_STREAM)
        .retrieve()
        .bodyToFlux(String.class)
        .log()
        .take(10)
        .blockLast();
  }
}
