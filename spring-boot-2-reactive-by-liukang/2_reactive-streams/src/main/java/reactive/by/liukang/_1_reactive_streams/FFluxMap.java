package reactive.by.liukang._1_reactive_streams;

import java.util.function.Function;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class FFluxMap<T, R> extends FFlux<R> {

  private final FFlux<? extends T> source;
  private final Function<? super T, ? extends R> mapper;

  public FFluxMap(FFlux<? extends T> source, Function<? super T, ? extends R> mapper) {
    this.source = source;
    this.mapper = mapper;
  }

  @Override
  public void subscribe(Subscriber<? super R> subscriber) {
    source.subscribe(new FMapSubscriber<>(subscriber, mapper));
  }

  static final class FMapSubscriber<T, R> implements Subscriber<T>, Subscription {
    private final Subscriber<? super R> subscriber;
    private final Function<? super T, ? extends R> mapper;

    boolean done;

    Subscription upstream;

    FMapSubscriber(Subscriber<? super R> actual, Function<? super T, ? extends R> mapper) {
      this.subscriber = actual;
      this.mapper = mapper;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
      this.upstream = subscription;
      subscriber.onSubscribe(this);
    }

    @Override
    public void onNext(T t) {
      if (done) {
        return;
      }
      subscriber.onNext(mapper.apply(t));
    }

    @Override
    public void onError(Throwable t) {
      if (done) {
        return;
      }
      done = true;
      subscriber.onError(t);
    }

    @Override
    public void onComplete() {
      if (done) {
        return;
      }
      done = true;
      subscriber.onComplete();
    }

    @Override
    public void request(long n) {
      this.upstream.request(n);
    }

    @Override
    public void cancel() {
      this.upstream.cancel();
    }
  }
}
