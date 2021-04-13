package reactive.webflux.by.stackabuse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

@Configuration
public class RouterConfiguration {

  @Autowired HelloService helloService;

  @Bean
  RouterFunction<ServerResponse> routes() {
    return route(
            GET("/route/hello"),
            request -> ServerResponse.ok().body(Flux.just("Hello World"), String.class))
        .andRoute(
            GET("/route/hi"), request -> ServerResponse.ok().body(Flux.just("Hi"), String.class))
        .andRoute(GET("route/helloworld"), helloService.helloWorld());
  }
}
