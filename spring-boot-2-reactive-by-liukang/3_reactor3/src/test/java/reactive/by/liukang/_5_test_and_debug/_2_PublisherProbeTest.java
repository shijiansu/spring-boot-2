package reactive.by.liukang._5_test_and_debug;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.publisher.PublisherProbe;

public class _2_PublisherProbeTest {

  @Test
  public void publisher_probe() {
    PublisherProbe<Void> probe = PublisherProbe.empty();
    StepVerifier.create(processOrFallback(Mono.empty(), probe.mono())).verifyComplete();
    probe.assertWasSubscribed();
    probe.assertWasRequested();
    probe.assertWasNotCancelled();
  }

  private Mono<String> executeCommand(String command) {
    // 基于command执行一些操作，执行完成后返回Mono<String>
    return Mono.just(command + " DONE");
  }

  private Mono<Void> processOrFallback(Mono<String> commandSource, Mono<Void> doWhenEmpty) {
    // can not use StepVerifier to verify, use PublisherProbe
    return commandSource
        .flatMap(command -> executeCommand(command).then()) // then() returns Mono<Void>
        .switchIfEmpty(doWhenEmpty); // also a Mono<Void>
  }
}
