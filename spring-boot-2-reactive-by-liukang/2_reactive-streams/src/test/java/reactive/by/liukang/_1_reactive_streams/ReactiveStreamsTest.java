package reactive.by.liukang._1_reactive_streams;

import static java.lang.System.err;
import static java.lang.System.out;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class ReactiveStreamsTest {
  @Test
  public void flux_array() {
    FFlux.just(1, 2, 3, 4, 5)
        .subscribe(
            new Subscriber<Integer>() {

              @Override
              public void onSubscribe(Subscription s) {
                out.println("onSubscribe: " + s);
                s.request(Integer.MAX_VALUE);
              }

              @Override
              public void onNext(Integer integer) {
                out.println("onNext:" + integer);
              }

              @Override
              public void onError(Throwable t) {
                out.println("onError:" + t.getMessage());
              }

              @Override
              public void onComplete() {
                out.println("onComplete");
              }
            });
  }

  @Test
  public void lambda_subscriber() {
    FFlux.just(1, 2, 3, 4, 5)
        .map(i -> i * 2)
        .subscribe(
            out::println, err::println, () -> out.println("Completed.")
            //                        subscription -> subscription.request(3)
            );
  }

  @Test
  public void lambda_subscriber_take_3() {
    FFlux.just(1, 2, 3, 4, 5)
        .map(i -> i * 2)
        .subscribe(
            out::println,
            err::println,
            () -> out.println("Completed."),
            subscription -> subscription.request(3));
  }
}
