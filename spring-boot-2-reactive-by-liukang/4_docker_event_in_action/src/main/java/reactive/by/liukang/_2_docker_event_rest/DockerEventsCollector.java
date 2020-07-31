package reactive.by.liukang._2_docker_event_rest;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Event;
import com.github.dockerjava.api.model.EventType;
import com.github.dockerjava.api.model.Node;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.EventsResultCallback;
import java.util.Objects;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Slf4j
@Component
public class DockerEventsCollector implements CommandLineRunner {

  private DockerEventRepository dockerEventRepository;
  private MongoTemplate mongo;

  public DockerEventsCollector(DockerEventRepository dockerEventRepository, MongoTemplate mongo) {
    this.dockerEventRepository = dockerEventRepository;
    this.mongo = mongo;
  }

  @Override
  public void run(String... args) {
    mongo.dropCollection(DockerEvent.class);
    mongo.createCollection(
        DockerEvent.class, CollectionOptions.empty().maxDocuments(200).size(100_000).capped());
    Flux.concat(
            dockerEventRepository.save(
                firstEvent()), // create 1st record that @Tailable not return and close
            dockerEventRepository.saveAll(collect()))
        .subscribe();
  }

  private Flux<DockerEvent> collect() {
    DockerClient docker = DockerClientBuilder.getInstance().build();
    return Flux.create(
            (FluxSink<Event> sink) -> {
              EventsResultCallback callback =
                  new EventsResultCallback() {
                    @Override
                    public void onNext(Event event) {
                      sink.next(event);
                    }
                  };
              docker.eventsCmd().exec(callback);
            })
        .map(this::convert)
        .doOnNext(e -> log.info(e.toString()));
  }

  private DockerEvent firstEvent(){
    DockerEvent dockerEvent = new DockerEvent();
    dockerEvent.setAction("Mock Data");
    dockerEvent.setActorId("00000000");
    dockerEvent.setFrom("SpringBoot");
    dockerEvent.setId(UUID.randomUUID().toString());
    dockerEvent.setNode(new Node());
    dockerEvent.setStatus("COMPLETED");
    dockerEvent.setTime(System.currentTimeMillis());
    dockerEvent.setTimeNano(System.nanoTime());
    dockerEvent.setType(EventType.CONTAINER);
    return dockerEvent;
  }

  private DockerEvent convert(Event event) {
    DockerEvent dockerEvent = new DockerEvent();
    dockerEvent.setAction(event.getAction());
    dockerEvent.setActorId(Objects.requireNonNull(event.getActor()).getId());
    dockerEvent.setFrom(event.getFrom() == null ? null : event.getFrom().replace("//", "_"));
    dockerEvent.setId(UUID.randomUUID().toString());
    dockerEvent.setNode(event.getNode());
    dockerEvent.setStatus(event.getStatus());
    dockerEvent.setTime(event.getTime());
    dockerEvent.setTimeNano(event.getTimeNano());
    dockerEvent.setType(event.getType());
    return dockerEvent;
  }
}
