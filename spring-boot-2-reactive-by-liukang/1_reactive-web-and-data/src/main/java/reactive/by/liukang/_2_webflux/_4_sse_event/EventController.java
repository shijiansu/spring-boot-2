package reactive.by.liukang._2_webflux._4_sse_event;

import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/events")
public class EventController {
  @Autowired private EventRepository eventRepository;

  @PostMapping(path = "/", consumes = APPLICATION_STREAM_JSON_VALUE)
  public Mono<Void> loadEvents(@RequestBody Flux<Event> events) {
    return eventRepository.insert(events).then(); // then方法表示“忽略数据元素，只返回一个完成信号”
  }

  @GetMapping(path = "/", produces = APPLICATION_STREAM_JSON_VALUE)
  public Flux<Event> getEvents() {
    return eventRepository.findBy();
  }
}
