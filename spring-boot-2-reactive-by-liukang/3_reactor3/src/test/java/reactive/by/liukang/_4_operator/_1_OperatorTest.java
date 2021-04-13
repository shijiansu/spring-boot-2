package reactive.by.liukang._4_operator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

@Slf4j
public class _1_OperatorTest {

  @Test
  public void transform() {
    Function<Flux<String>, Flux<String>> filterAndMap =
        f -> f.filter(color -> !color.equals("orange")).map(String::toUpperCase);

    Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
        .doOnNext(System.out::println)
        .transform(filterAndMap) // package
        .subscribe(d -> log.info("Subscriber to Transformed MapAndFilter: " + d));
  }

  @Test
  public void transform2() {
    StringBuilder s1 = new StringBuilder();
    Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
        .doOnNext(System.out::println)
        .filter(color -> !color.equals("orange"))
        .map(String::toUpperCase)
        .subscribe(s1::append);
    log.info("Subscriber to Transformed MapAndFilter - s1: {}", s1.toString());
    // ----------------------------------------
    // Same as above
    StringBuilder s2 = new StringBuilder();
    Function<Flux<String>, Flux<String>> filterAndMap =
        f -> f.filter(color -> !color.equals("orange")).map(String::toUpperCase);

    Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
        .doOnNext(System.out::println)
        .transform(filterAndMap) // package
        .subscribe(s2::append);
    log.info("Subscriber to Transformed MapAndFilter - s2: {}", s2.toString());

    assertEquals(s1.toString(), s2.toString());
  }

  @Test
  public void transform_vs_transformDeferred_1_by_transform() {
    AtomicInteger ai = new AtomicInteger();
    Function<Flux<String>, Flux<String>> filterAndMap =
        f -> {
          if (ai.incrementAndGet() == 1) {
            return f.filter(color -> !color.equals("orange")).map(String::toUpperCase);
          }
          return f.filter(color -> !color.equals("purple")) // never reach in this test case
              .map(String::toUpperCase);
        };

    Flux<String> composedFlux =
        Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
            .doOnNext(log::info)
            .transform(filterAndMap);

    composedFlux.subscribe(d -> log.info("Subscriber 1 to Composed MapAndFilter: " + d));
    composedFlux.subscribe(d -> log.info("Subscriber 2 to Composed MapAndFilter: " + d));
  }

  @Test
  public void transform_vs_transformDeferred_2_by_transformDeferred() { // replace compose()
    // compare with transfer, it is 针对每一个订阅者起作用的
    AtomicInteger ai = new AtomicInteger();
    Function<Flux<String>, Flux<String>> filterAndMap =
        f -> {
          if (ai.incrementAndGet() == 1) { // increase after get, 1st subscriber comes here
            return f.filter(color -> !color.equals("orange")).map(String::toUpperCase);
          }
          return f.filter(color -> !color.equals("purple")).map(String::toUpperCase);
        };

    Flux<String> composedFlux =
        Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
            .doOnNext(log::info)
            .transformDeferred(filterAndMap);

    composedFlux.subscribe(d -> log.info("Subscriber 1 to Composed MapAndFilter: " + d));
    composedFlux.subscribe(d -> log.info("Subscriber 2 to Composed MapAndFilter: " + d));
  }
}
