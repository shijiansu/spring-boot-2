package reactive.by.liukang._2_webflux._3_reactive_data;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, String> {
  Mono<User> findByUsername(String username);

  Mono<Long> deleteByUsername(String username);
}
