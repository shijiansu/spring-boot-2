package reactive.mongodb.by.stackabuse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class ReservationDataInitializer implements ApplicationRunner {

  Logger logger = LoggerFactory.getLogger(ReservationDataInitializer.class);

  @Autowired ReservationRepository repository;

  @Override
  public void run(ApplicationArguments args) {
    repository
        .deleteAll()
        .thenMany(Flux.just("Jane", "John", "Max", "Josh", "Aloy"))
        .map(name -> new Reservation(null, name))
        .flatMap(repository::save)
        .thenMany(repository.findAll())
        .subscribe(r -> logger.info("{}", r));
  }
}
