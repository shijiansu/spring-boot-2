package reactive.webclient.by.stackabuse;

import java.time.Duration;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class _1_MonoFluxTest {
  class MonoFlux {

    public Flux<String> createFluxFromValues() {
      return Flux.just("foo", "bar", "foobar");
    }

    public Flux<String> createFluxFromList() {
      return Flux.fromIterable(Arrays.asList("foo", "bar", "foobar"));
    }

    // Flux that emits increasing values from 0 to 9 each 100ms
    public Flux<Long> counter() {
      return Flux.interval(Duration.ofMillis(100)).take(10);
    }

    public Mono<String> fooMono() {
      return Mono.just("foo");
    }
  }

  MonoFlux monoFlux = new MonoFlux();

  @Test
  public void fromValues() {
    Flux<String> flux = monoFlux.createFluxFromValues();
    StepVerifier.create(flux).expectNext("foo", "bar", "foobar").verifyComplete();
  }

  @Test
  public void fromList() {
    Flux<String> flux = monoFlux.createFluxFromList();
    StepVerifier.create(flux).expectNext("foo", "bar", "foobar").verifyComplete();
  }
}
