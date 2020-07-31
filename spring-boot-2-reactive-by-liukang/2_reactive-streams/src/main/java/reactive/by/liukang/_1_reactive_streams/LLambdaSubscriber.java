package reactive.by.liukang._1_reactive_streams;

import java.util.function.Consumer;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class LLambdaSubscriber<T> implements Subscriber<T> {

  final Consumer<? super T> consumer;
  final Consumer<? super Throwable> errorConsumer;
  final Runnable completeConsumer;
  final Consumer<? super Subscription> subscriptionConsumer;

  public LLambdaSubscriber(
      Consumer<? super T> consumer,
      Consumer<? super Throwable> errorConsumer,
      Runnable completeConsumer,
      Consumer<? super Subscription> subscriptionConsumer) {
    this.consumer = consumer;
    this.errorConsumer = errorConsumer;
    this.completeConsumer = completeConsumer;
    this.subscriptionConsumer = subscriptionConsumer;
  }

  @Override
  public void onSubscribe(Subscription subscription) {
    if (subscriptionConsumer != null) {
      subscriptionConsumer.accept(subscription);
    } else {
      subscription.request(Long.MAX_VALUE);
    }
  }

  @Override
  public void onNext(T t) {
    if (consumer != null) {
      consumer.accept(t);
    }
  }

  @Override
  public void onError(Throwable throwable) {
    if (errorConsumer != null) {
      errorConsumer.accept(throwable);
    }
  }

  @Override
  public void onComplete() {
    if (completeConsumer != null) {
      completeConsumer.run();
    }
  }
}
