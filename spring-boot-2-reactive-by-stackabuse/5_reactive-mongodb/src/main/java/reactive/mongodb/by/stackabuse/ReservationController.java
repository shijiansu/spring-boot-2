package reactive.mongodb.by.stackabuse;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ReservationController {

  @Autowired ReservationRepository reservationRepository;

  @GetMapping(value = "/reservations", produces = APPLICATION_JSON_VALUE)
  public Flux<Reservation> reservations() {
    return reservationRepository.findAll();
  }
}
