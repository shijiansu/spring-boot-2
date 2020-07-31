package reactive.by.liukang._2_webflux._4_sse_event;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

public interface EventRepository extends ReactiveMongoRepository<Event, Long> {
  // 类似于linux的tail命令，被注解的方法将发送无限流
  // @Tailable仅支持有大小限制的（“capped”）collection，
  // 而自动创建的collection是不限制大小的，因此我们需要先手动创建。
  // Spring Boot提供的CommandLineRunner可以帮助我们实现这一点。
  // @Tailable起作用的前提是至少有一条记录
  @Tailable
  Flux<Event> findBy();
}
