package reactive.by.liukang._3_scheduler;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class _1_SchedulerTest {

  @Test
  public void scheduler_thread_1() {
    Flux.range(0, 10)
        .log()
        .publishOn(Schedulers.newParallel("myParallel"))
        .subscribeOn(Schedulers.newElastic("myElastic"))
        .blockLast();
    //    15:11:22 [myElastic-2              ] 1 - | onSubscribe([Synchronous Fuseable]
    // FluxRange.RangeSubscription)
    //    15:11:22 [myElastic-2              ] 1 - | request(256)
    //    15:11:22 [myElastic-2              ] 1 - | onNext(0)
  }

  @Test
  public void scheduler_thread_2() {
    Flux.range(0, 10)
        .publishOn(Schedulers.newParallel("myParallel"))
        .log()
        .subscribeOn(Schedulers.newElastic("myElastic"))
        .blockLast();
    //    15:10:49 [myElastic-2              ] 1 - | onSubscribe([Fuseable]
    // FluxPublishOn.PublishOnSubscriber)
    //    15:10:49 [myElastic-2              ] 1 - | request(unbounded)
    //    15:10:49 [myParallel-1             ] 1 - | onNext(0)
  }

  @Test
  public void scheduler_thread_3() {
    Flux.range(0, 10)
        .publishOn(Schedulers.newParallel("myParallel"))
        .subscribeOn(Schedulers.newElastic("myElastic"))
        .log()
        .blockLast();
    //    15:09:57 [main                     ] 1 -
    // onSubscribe(FluxSubscribeOn.SubscribeOnSubscriber)
    //    15:09:57 [main                     ] 1 - request(unbounded)
    //    15:09:57 [myParallel-1             ] 1 - onNext(0)
  }

  @Test
  public void scheduler_thread_4() {
    Flux.range(0, 10)
        .delayElements(Duration.ofMillis(10)) // 使用内置的单例调度器来执行
        .log()
        .blockLast();
    //    16:01:16 [main                     ] 1 - onSubscribe(FluxConcatMap.ConcatMapImmediate)
    //    16:01:16 [main                     ] 1 - request(unbounded)
    //    16:01:16 [parallel-1               ] 1 - onNext(0)
  }

  @Test
  public void parallel_flux_but_still_single() throws InterruptedException {
    CountDownLatch latch = new CountDownLatch(1);
    Flux.range(1, 10)
        .publishOn(Schedulers.parallel())
        .doOnComplete(latch::countDown)
        .log()
        .subscribe();
    latch.await(1, TimeUnit.MINUTES);
  }

  @Test
  public void parallel_flux() throws InterruptedException {
    CountDownLatch latch = new CountDownLatch(1);
    Flux.range(1, 10)
        .parallel(2)
        .runOn(Schedulers.parallel())
        .doOnComplete(latch::countDown)
        .log()
        .subscribe();
    latch.await(1, TimeUnit.MINUTES);
  }
}
