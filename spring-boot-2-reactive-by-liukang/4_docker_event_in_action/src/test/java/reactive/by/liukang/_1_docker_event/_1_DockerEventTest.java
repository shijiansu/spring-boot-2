package reactive.by.liukang._1_docker_event;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Event;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.EventsResultCallback;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

@Slf4j
public class _1_DockerEventTest {
  DockerClient docker;

  @BeforeEach
  public void init() {
    docker = DockerClientBuilder.getInstance().build(); // 默认会连接tcp://localhost:2375
  }

  @Disabled("Because it needs manually execute - docker run hello-world")
  @Test
  public void v1_docker_event_to_flux_manul_docker() throws InterruptedException, IOException {
    collectDockerEvents().subscribe(System.out::println);
    // docker run hello-world
    TimeUnit.MINUTES.sleep(1); // keep main thread alive
  }

  @Disabled("Because it waits up to a min to complete")
  @Test
  public void v2_docker_event_to_flux_wait_1_min() throws InterruptedException {
    collectDockerEvents().subscribe(System.out::println);
    TimeUnit.SECONDS.sleep(5); // wait above flux to be ready
    runDocker();
    TimeUnit.MINUTES.sleep(1); // keep main thread alive
  }

  @Test
  public void docker_event_to_flux() throws InterruptedException {
    CountDownLatch latch = new CountDownLatch(1);
    collectDockerEvents().doOnNext(e -> latch.countDown()).subscribe(System.out::println);
    TimeUnit.SECONDS.sleep(2); // wait above flux to be ready. Need to find a way to remove
    runDocker();
    latch.await(1, TimeUnit.MINUTES);
    // dockerclient bootup -> exec -> terminal docker run -> event ->
    // collectDockerEvents -> emmit Flux -> subscribe -> print -> doOnNext -> count down -> latch
    // finish waiting -> exit main
  }

  @SneakyThrows
  private Process runDocker() {
    Process process = new ProcessBuilder().command("bash", "-c", "docker run hello-world").start();
    log.info("process: {}", process);
    return process;
  }

  private Flux<Event> collectDockerEvents() {
    return Flux.create(
        (FluxSink<Event> sink) -> {
          EventsResultCallback callback =
              new EventsResultCallback() {
                @Override
                public void onNext(Event event) {
                  sink.next(event);
                }
              };
          log.info("callback: {}", callback);
          docker.eventsCmd().exec(callback); // this one take times
          log.info("callback after exec");
        });
  }
}
