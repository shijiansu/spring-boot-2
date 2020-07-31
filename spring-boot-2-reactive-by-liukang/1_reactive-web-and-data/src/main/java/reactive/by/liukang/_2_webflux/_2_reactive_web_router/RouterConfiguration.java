package reactive.by.liukang._2_webflux._2_reactive_web_router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfiguration {
  @Autowired private TimeHandler timeHandler;

  @Bean
  public RouterFunction<ServerResponse> routers() {
    return route(GET("/time"), timeHandler::getTime)
        .andRoute(GET("/date"), timeHandler::getDate)
        .andRoute(GET("/times"), timeHandler::sendTimePerSec);
  }
}
