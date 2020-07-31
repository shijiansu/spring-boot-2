package reactive.by.liukang._1_reactor_api;

import reactive.by.liukang._1_reactor_api.EEventSource.EEvent;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class _1_ReactorTest {
  // generate是一种同步地，逐个地发出数据的方法。
  // 因为它提供的sink是一个SynchronousSink，
  // 而且其next()方法在每次回调的时候最多只能被调用一次
  @Test
  public void generate() {
    final AtomicInteger count = new AtomicInteger(1);
    Flux.generate( // Consumer, input is sink
            sink -> { // reactor.core.publisher.SynchronousSink<Object>
              sink.next(count.get() + " : " + new Date());
              try {
                TimeUnit.SECONDS.sleep(1);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              if (count.getAndIncrement() >= 5) {
                sink.complete();
              }
            })
        .subscribe(System.out::println);
  }

  @Test
  public void generate_2() {
    Flux.generate(
            () -> 1, // init counter
            (count, sink) -> {
              sink.next(count + " : " + new Date());
              try {
                TimeUnit.SECONDS.sleep(1);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              if (count >= 5) {
                sink.complete();
              }
              return count + 1; // 每次循环都要返回新的状态值给下次使用
            })
        .subscribe(System.out::println);
  }

  @Test
  public void generate_3() {
    Flux.generate(
            () -> 1, // S is Integer
            (count, sink) -> { // S is Integer, T is SynchronousSink as next sink
              sink.next(count + " : " + new Date());
              try {
                TimeUnit.SECONDS.sleep(1);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              if (count >= 5) {
                sink.complete();
              }
              return count + 1; // R is Interger
            },
            System.out::println)
        .subscribe(System.out::println); // completion comsumer
  }

  // create是一个更高级的创建Flux的方法，其生成数据流的方式既可以是同步的，也可以是异步的，并且还可以每次发出多个元素。
  // create用到了FluxSink，后者同样提供 next，error 和 complete 等方法。
  // 与generate不同的是，create不需要状态值，另一方面，它可以在回调中触发多个事件（即使事件是发生在未来的某个时间）。
  @Test
  public void create() throws InterruptedException {
    EEventSource eventSource = new EEventSource();
    Flux.create(
            sink ->
                eventSource.register(
                    new EEventListener() { // wrapper class for sink
                      @Override
                      public void onNewEvent(EEvent event) {
                        sink.next(event);
                      }

                      @Override
                      public void onEventStopped() {
                        sink.complete();
                      }
                    }))
        .subscribe(System.out::println);

    for (int i = 0; i < 20; i++) {
      Random random = new Random();
      TimeUnit.MILLISECONDS.sleep(random.nextInt(1_000));
      eventSource.newEvent(new EEvent(new Date(), "Event-" + i)); // trigger onNewEvent()
    }
    eventSource.eventStopped();
  }
}
