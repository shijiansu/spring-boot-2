package reactive.webflux.by.stackabuse;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import reactor.core.publisher.Flux;

@Configuration
public class WebSocketConfiguration {

  Logger logger = LoggerFactory.getLogger(WebSocketConfiguration.class);

  @Bean
  WebSocketHandlerAdapter webSocketHandlerAdapter() {
    return new WebSocketHandlerAdapter();
  }

  @Bean
  WebSocketHandler webSocketHandler() {
    return session -> {
      Flux<WebSocketMessage> generate =
          Flux.<Greeting>generate(
                  sink -> sink.next(new Greeting("Hello @" + Instant.now().toString())))
              .map(g -> session.textMessage(g.getText()))
              .delayElements(Duration.ofSeconds(1))
              .doFinally(
                  signalType ->
                      logger.info(signalType.name() + ": Goodbye!")); // when close browser
      return session.send(generate);
    };
  }

  @Bean
  HandlerMapping handlerMapping() {
    SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
    simpleUrlHandlerMapping.setUrlMap(Collections.singletonMap("/ws/hello", webSocketHandler()));
    simpleUrlHandlerMapping.setOrder(10);
    return simpleUrlHandlerMapping;
  }
}
