package reactive.by.liukang._1_reactive_streams;

import java.util.function.Consumer;
import java.util.function.Function;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

// publisher as well as util
public abstract class FFlux<T> implements Publisher<T> {

  public abstract void subscribe(Subscriber<? super T> s);

  public void subscribe(
      Consumer<? super T> consumer,
      Consumer<? super Throwable> errorConsumer,
      Runnable completeConsumer,
      Consumer<? super Subscription> subscriptionConsumer) {
    subscribe(
        new LLambdaSubscriber<>(consumer, errorConsumer, completeConsumer, subscriptionConsumer));
  }

  public void subscribe(Consumer<? super T> consumer) {
    subscribe(consumer, null, null, null);
  }

  public void subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> errorConsumer) {
    subscribe(consumer, errorConsumer, null, null);
  }

  public void subscribe(
      Consumer<? super T> consumer,
      Consumer<? super Throwable> errorConsumer,
      Runnable completeConsumer) {
    subscribe(consumer, errorConsumer, completeConsumer, null);
  }

  public static <T> FFlux<T> just(T... data) {
    return new FFluxArray<>(data);
  }

  public <V> FFlux<V> map(Function<? super T, ? extends V> mapper) {
    return new FFluxMap<>(this, mapper);
  }
}
