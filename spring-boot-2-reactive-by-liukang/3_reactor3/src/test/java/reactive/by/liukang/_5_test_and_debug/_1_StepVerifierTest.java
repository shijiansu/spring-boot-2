package reactive.by.liukang._5_test_and_debug;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class _1_StepVerifierTest {
  private Flux<String> appendBoomError(Flux<String> origin) {
    return origin.concatWith(Mono.error(new RuntimeException("boom")));
  }

  @Test
  public void appendboom_error() {
    Flux<String> source = Flux.just("foo", "bar");
    StepVerifier.create(appendBoomError(source))
        .expectNext("foo")
        .expectNext("bar")
        .expectErrorMessage("boom")
        .verify();
  }

  @Test
  public void virtual_time() {
    StepVerifier.withVirtualTime(() -> Mono.delay(Duration.ofDays(1)))
        .expectSubscription()
        .expectNoEvent(Duration.ofDays(1))
        .expectNext(0L)
        .verifyComplete();
  }



}
