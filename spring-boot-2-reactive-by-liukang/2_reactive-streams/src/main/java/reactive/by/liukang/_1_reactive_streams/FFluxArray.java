package reactive.by.liukang._1_reactive_streams;

import java.util.Arrays;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class FFluxArray<T> extends FFlux<T> {
  private T[] array;

  public FFluxArray(T[] array) {
    this.array = array;
  }

  @Override
  public void subscribe(Subscriber<? super T> subscriber) { // accept super interface / class of T
    // put subscription into subscriber
    subscriber.onSubscribe(new FArraySubscription<>(subscriber, array));
  }

  static class FArraySubscription<T> implements Subscription {
    final Subscriber<? super T> subscriber;
    final T[] array;
    int index;
    boolean canceled;

    public FArraySubscription(Subscriber<? super T> subscriber, T[] array) {
      this.subscriber = subscriber;
      this.array = array;
    }

    @Override
    public void request(long n) {
      if (canceled) {
        return;
      }
      long length = array.length;
      for (int i = 0; i < n && index < length; i++) {
        if (canceled) {
          return;
        }
        subscriber.onNext(array[index++]);
      }
      if (index == length) {
        subscriber.onComplete();
      }
    }

    @Override
    public void cancel() {
      this.canceled = true;
    }

    @Override
    public String toString() {
      return "ArraySubscription{"
          + "subscriber="
          + subscriber
          + ", array="
          + Arrays.toString(array)
          + ", index="
          + index
          + ", canceled="
          + canceled
          + '}';
    }
  }
}
