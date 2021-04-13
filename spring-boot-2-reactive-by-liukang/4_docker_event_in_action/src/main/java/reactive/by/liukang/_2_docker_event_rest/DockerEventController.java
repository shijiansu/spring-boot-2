package reactive.by.liukang._2_docker_event_rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequestMapping(value = "/docker/events", produces = MediaType.APPLICATION_STREAM_JSON_VALUE) // Server send event
public class DockerEventController {
  private DockerEventRepository dockerEventRepository;

  public DockerEventController(DockerEventRepository dockerEventRepository) {
    this.dockerEventRepository = dockerEventRepository;
  }

  @GetMapping
  public Flux<DockerEvent> dockerEventStream() {
    return dockerEventRepository.findBy();
  }

  @GetMapping("/type/{type}/from/{from}")
  public Flux<DockerEvent> dockerEventStream(
      @PathVariable("type") String type, @PathVariable("from") String from) {
    return dockerEventRepository.findByTypeAndFrom(type, from);
  }

  @GetMapping("/status/{status}")
  public Flux<DockerEvent> dockerEventStream(@PathVariable String status) {
    return dockerEventRepository.findByStatus(status);
  }
}
