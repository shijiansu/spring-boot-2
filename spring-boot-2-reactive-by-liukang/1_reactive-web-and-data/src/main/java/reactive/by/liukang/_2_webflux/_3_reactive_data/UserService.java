package reactive.by.liukang._2_webflux._3_reactive_data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
  @Autowired UserRepository userRepository;

  public Flux<User> findAll() {
    return userRepository.findAll().log();
  }

  public Mono<User> save(User user) {
    return userRepository
        .save(user)
        .doOnError(System.out::println)
        .onErrorResume(
            e ->
                userRepository
                    .findByUsername(user.getUsername())
                    .flatMap(
                        originalUser -> {
                          user.setId(originalUser.getId());
                          return userRepository.save(user);
                        }));
  }

  public Mono<Long> deleteByUsername(String username) {
    return userRepository.deleteByUsername(username);
  }

  public Mono<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }
}
