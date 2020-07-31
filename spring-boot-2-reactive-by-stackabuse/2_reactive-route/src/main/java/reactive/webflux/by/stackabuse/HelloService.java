package reactive.webflux.by.stackabuse;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

@Service
public class HelloService {
  HandlerFunction<ServerResponse> helloWorld() {
    return request -> ServerResponse.ok().body(Flux.just("Hello World"), String.class);
  }
}
