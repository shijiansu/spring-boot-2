package reactive.by.liukang._2_docker_event_rest;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

public interface DockerEventRepository extends ReactiveMongoRepository<DockerEvent, String> {
  //  再次提醒，当capped 的 Collection中一条数据都没有的时候，
  //  @Tailable的API也会立刻返回，所以需要等到数据库中有至少一条数据之后，
  //  再在浏览器中请求docker/eventsAPI。
  @Tailable
  Flux<DockerEvent> findByStatus(String status);

  @Tailable
  Flux<DockerEvent> findByTypeAndFrom(String type, String from);

  @Tailable
  Flux<DockerEvent> findBy();
}
