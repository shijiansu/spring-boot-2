package reactive.by.liukang._4_see_event;

import static java.lang.System.currentTimeMillis;

import java.time.Duration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactive.by.liukang._2_webflux._4_sse_event.Event;
import reactive.by.liukang._2_webflux._4_sse_event.EventConfiguration;
import reactive.by.liukang._2_webflux._4_sse_event.EventController;
import reactor.core.publisher.Flux;

public class EventControllerManualTest {
  @Test
  public void insert_5_events() {
    Flux<Event> eventFlux =
        Flux.interval(Duration.ofSeconds(1))
            .map(l -> new Event(currentTimeMillis(), "message-" + l))
            .take(5); // create 5
    WebClient webClient = WebClient.create("http://localhost:8080");
    webClient
        .post()
        .uri("/events")
        .contentType(MediaType.APPLICATION_STREAM_JSON)
        .body(eventFlux, Event.class)
        .retrieve()
        .bodyToMono(Void.class)
        .block();
  }
}
