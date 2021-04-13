package reactive.webclient.by.stackabuse;

import static java.time.Duration.ofSeconds;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

import java.util.Date;
import java.util.stream.Stream;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

@RestController
public class GreetingController {

  private Greeting greeting() {
    return new Greeting("Hello @ " + new Date());
  }

  // client wait for 10s to be ready to download content
  @GetMapping(value = "/greeting", produces = APPLICATION_JSON_VALUE)
  public Publisher<Greeting> greetingPublisher() {
    Flux<Greeting> greetingFlux =
        Flux.<Greeting>generate(sink -> sink.next(greeting())).delayElements(ofSeconds(1)).take(10);
    return greetingFlux;
  }

  // FLux is Publisher - client wait for 10s
  @GetMapping(value = "/greeting/flux", produces = APPLICATION_JSON_VALUE)
  public Flux<Greeting> greetingFlux() {
    Flux<Greeting> greetingFlux =
        Flux.<Greeting>generate(sink -> sink.next(greeting())).delayElements(ofSeconds(1)).take(10);
    return greetingFlux;
  }

  // Server send event - client wait for 1s, take 10s to complete download
  @GetMapping(value = "/greeting/sse", produces = TEXT_EVENT_STREAM_VALUE)
  public Publisher<Greeting> greetingSse() {
    Flux<Greeting> delayElements =
        Flux.<Greeting>generate(sink -> sink.next(greeting())).delayElements(ofSeconds(1)).take(10);
    return delayElements;
  }
  //  data:{"text":"Hello @ Thu Mar 05 18:37:29 SGT 2020"}
  //  data:{"text":"Hello @ Thu Mar 05 18:37:29 SGT 2020"}
  //  data:{"text":"Hello @ Thu Mar 05 18:37:29 SGT 2020"}
  //  data:{"text":"Hello @ Thu Mar 05 18:37:29 SGT 2020"}
  //  data:{"text":"Hello @ Thu Mar 05 18:37:29 SGT 2020"}
  //  data:{"text":"Hello @ Thu Mar 05 18:37:29 SGT 2020"}
  //  data:{"text":"Hello @ Thu Mar 05 18:37:29 SGT 2020"}
  //  data:{"text":"Hello @ Thu Mar 05 18:37:29 SGT 2020"}
  //  data:{"text":"Hello @ Thu Mar 05 18:37:29 SGT 2020"}
  //  data:{"text":"Hello @ Thu Mar 05 18:37:29 SGT 2020"}

  @GetMapping(value = "/greeting/sse2", produces = TEXT_EVENT_STREAM_VALUE)
  Flux<Greeting> greetingSseZip() {
    Flux<Greeting> greetingFlux = Flux.fromStream(Stream.generate(() -> greeting())).take(10);
    Flux<Long> durationFlux = Flux.interval(ofSeconds(1));
    return Flux.zip(greetingFlux, durationFlux) // zip is 1 match 1, it makes it wait 1s
        .map(Tuple2::getT1); // return greetingFlux
  }
  //  data:{"text":"Hello @ Thu Mar 05 18:35:49 SGT 2020"}
  //  data:{"text":"Hello @ Thu Mar 05 18:35:50 SGT 2020"}
  //  data:{"text":"Hello @ Thu Mar 05 18:35:51 SGT 2020"}
  //  data:{"text":"Hello @ Thu Mar 05 18:35:52 SGT 2020"}
  //  data:{"text":"Hello @ Thu Mar 05 18:35:53 SGT 2020"}
  //  data:{"text":"Hello @ Thu Mar 05 18:35:54 SGT 2020"}
  //  data:{"text":"Hello @ Thu Mar 05 18:35:55 SGT 2020"}
  //  data:{"text":"Hello @ Thu Mar 05 18:35:56 SGT 2020"}
  //  data:{"text":"Hello @ Thu Mar 05 18:35:57 SGT 2020"}
  //  data:{"text":"Hello @ Thu Mar 05 18:35:58 SGT 2020"}
}
