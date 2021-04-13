package reactive.by.liukang._5_test_and_debug;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;

public class _4_DebugManualTest {
  private Mono<Integer> getMonoWithException() {
    return Flux.range(1, 5)
        .map(i -> i * i)
        .filter(i -> (i % 2) == 0)
        .single(); // 方法只能接收一个元素，多了的话就会导致异常
  }

  @Test
  public void bug() {
    getMonoWithException().subscribe();
  }

  @Test
  public void bug2_globally_and_costly() {
    Hooks.onOperatorDebug(); // global setting
    getMonoWithException().subscribe();
    Hooks.resetOnOperatorDebug();
  }

  @Test
  public void bug3() {
    getMonoWithException().checkpoint("to see if there is exception").subscribe();
  }

  @Test
  public void bug4() {
    getMonoWithException()
        // true - > 调试信息assembly traceback
        .checkpoint("to see if there is exception", true)
        .subscribe();
  }

  @Test
  public void log() {
    Flux.range(1, 10)
        .log()
        .take(3)
        .blockLast();
  }

  @Test
  public void log2() {
    Flux.range(1, 10).delayElements(Duration.ofMillis(10)).log().take(3).blockLast();
  }
}
