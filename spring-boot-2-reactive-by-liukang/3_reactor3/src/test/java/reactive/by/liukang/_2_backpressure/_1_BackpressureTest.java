package reactive.by.liukang._2_backpressure;

import static java.util.concurrent.TimeUnit.MINUTES;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactive.by.liukang._1_reactor_api.EEventListener;
import reactive.by.liukang._1_reactor_api.EEventSource;
import reactive.by.liukang._1_reactor_api.EEventSource.EEvent;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.FluxSink.OverflowStrategy;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class _1_BackpressureTest {

  private Flux<EEvent> fastPublisher; // will be subscribe at @AfterEach
  private SlowSubscriber slowSubscriber;
  private EEventSource eventSource;
  private CountDownLatch countDownLatch;

  @BeforeEach
  public void setup() {
    countDownLatch = new CountDownLatch(1); // count is 1, count down at hookOnComplete()
    slowSubscriber = new SlowSubscriber();
    eventSource = new EEventSource();
  }

  @AfterEach
  public void subscribe() throws InterruptedException {
    fastPublisher.subscribe(slowSubscriber);
    generateEEvent(20, 10);
    countDownLatch.await(1, MINUTES); // wait all subscribers complete, timeout is 1 min
  }

  @Test
  public void with_backpressure_stratety_buffer() {
    fastPublisher =
        createFlux(OverflowStrategy.BUFFER) // BUFFER/DROP/LATEST/ERROR/IGNORE
            .doOnRequest(n -> log.info("===  request (BUFFER): " + n + " ==="))
            .publishOn(Schedulers.newSingle("Publisher newSingle"), 2); // each time process 2
  }

  @Test
  public void with_backpressure_stratety_drop() {
    fastPublisher =
        createFlux(FluxSink.OverflowStrategy.DROP) // BUFFER/DROP/LATEST/ERROR/IGNORE
            .doOnRequest(n -> log.info("===  request (DROP): " + n + " ==="))
            .publishOn(Schedulers.newSingle("Publisher newSingle"), 2);
  }

  @Test
  public void with_backpressure_stratety_latest() {
    fastPublisher =
        createFlux(OverflowStrategy.LATEST) // BUFFER/DROP/LATEST/ERROR/IGNORE
            .doOnRequest(n -> log.info("===  request (LATEST): " + n + " ==="))
            .publishOn(Schedulers.newSingle("Publisher newSingle"), 2);
  }

  @Test
  public void with_backpressure_stratety_error() {
    fastPublisher =
        createFlux(OverflowStrategy.ERROR) // BUFFER/DROP/LATEST/ERROR/IGNORE
            .doOnRequest(n -> log.info("===  request (ERROR): " + n + " ==="))
            .publishOn(Schedulers.newSingle("Publisher newSingle"), 2);
  }

  @Test
  public void with_backpressure_stratety_ignore() {
    fastPublisher =
        createFlux(OverflowStrategy.IGNORE) // BUFFER/DROP/LATEST/ERROR/IGNORE
            .doOnRequest(n -> log.info("===  request (IGNORE): " + n + " ==="))
            .publishOn(Schedulers.newSingle("Publisher newSingle"), 2);
  }

  /** 测试不同的onBackpressureXxx方法的效果。 */
  @Test
  public void overwrite_by_on_backpressureXxx_setting() {
    fastPublisher =
        createFlux(OverflowStrategy.IGNORE)
            // .onBackpressureDrop()
            .onBackpressureBuffer() // override above OverflowStrategy setting; Can put Comsumer inside
            // .onBackpressureLatest()
            // .onBackpressureError()
            .doOnRequest(n -> log.info("===  request: " + n + " ==="))
            .publishOn(Schedulers.newSingle("Publisher newSingle"), 1);
  }

  @Test
  public void similar_operators() {
    fastPublisher =
        createFlux(FluxSink.OverflowStrategy.BUFFER)
            .doOnRequest(n -> log.info("===  request: " + n + " ==="))
            .sample(Duration.ofMillis(30));
  }

  private Flux<EEvent> createFlux(FluxSink.OverflowStrategy strategy) {
    return Flux.create(
        sink ->
            eventSource.register(
                new EEventListener() {
                  @Override
                  public void onNewEvent(EEvent event) {
                    log.info("publish >>> " + event.getMessage());
                    sink.next(event);
                  }

                  @Override
                  public void onEventStopped() {
                    sink.complete();
                  }
                }),
        strategy);
  }

  private void generateEEvent(int totalEvents, int ms) {
    for (int i = 0; i < totalEvents; i++) {
      try {
        TimeUnit.MILLISECONDS.sleep(ms);
      } catch (InterruptedException ignored) {
      }
      // running on main thread
      eventSource.newEvent(new EEvent(new Date(), "EEvent-" + i));
    }
    eventSource.eventStopped();
  }

  class SlowSubscriber extends BaseSubscriber<EEvent> {

    @Override
    protected void hookOnSubscribe(Subscription subscription) {
      request(1);
    }

    @Override
    protected void hookOnNext(EEvent event) {
      log.info("                      receive <<< " + event.getMessage());
      try {
        TimeUnit.MILLISECONDS.sleep(30);
      } catch (InterruptedException ignored) {
      }
      request(1);
    }

    @Override
    protected void hookOnError(Throwable throwable) {
      log.error("                      receive <<< " + throwable);
      countDownLatch.countDown();
    }

    @Override
    protected void hookOnComplete() {
      // control count down
      countDownLatch.countDown();
    }
  }
}
