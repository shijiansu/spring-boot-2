package reactive.by.liukang._6_cold_vs_hot;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

public class _1_ColdAndHotTest {
  @Test
  public void cold_is_subscribed_from_beginning() {
    Flux<String> source =
        Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
            .map(String::toUpperCase);

    source.subscribe(d -> System.out.println("Subscriber 1: " + d));
    System.out.println();
    source.subscribe(d -> System.out.println("Subscriber 2: " + d));
    // 2 times subscription results are the same
  }

  @Test
  public void hot_is_diff_each_subscription() {
    UnicastProcessor<String> hotSource = UnicastProcessor.create();

    Flux<String> hotFlux = hotSource.publish().autoConnect().map(String::toUpperCase);

    hotFlux.subscribe(d -> System.out.println("Subscriber 1 to Hot Source: " + d));

    hotSource.onNext("blue");
    hotSource.onNext("green");

    // do not get blue and green because had emitted
    hotFlux.subscribe(d -> System.out.println("Subscriber 2 to Hot Source: " + d));

    hotSource.onNext("orange");
    hotSource.onNext("purple");
    hotSource.onComplete();
  }
}
