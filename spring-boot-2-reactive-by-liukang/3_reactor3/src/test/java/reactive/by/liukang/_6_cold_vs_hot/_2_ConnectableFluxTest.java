package reactive.by.liukang._6_cold_vs_hot;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import reactor.core.Disposable;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public class _2_ConnectableFluxTest {
  @Test
  public void connectable_flux() throws InterruptedException {
    Flux<Integer> source = Flux.range(1, 3).doOnSubscribe(s -> System.out.println("上游收到订阅"));

    ConnectableFlux<Integer> co = source.publish();

    co.subscribe(System.out::println, e -> {}, () -> {});
    co.subscribe(System.out::println, e -> {}, () -> {});

    System.out.println("订阅者完成订阅操作");
    Thread.sleep(500);
    System.out.println("还没有连接上");
    co.connect(); // 手动触发对上游源的订阅
  }

  @Test
  public void connectable_flux_auto_connect() throws InterruptedException {
    Flux<Integer> source = Flux.range(1, 3).doOnSubscribe(s -> System.out.println("上游收到订阅"));

    // 需要两个订阅者才自动连接
    Flux<Integer> autoCo = source.publish().autoConnect(2);

    autoCo.subscribe(System.out::println, e -> {}, () -> {});
    System.out.println("第一个订阅者完成订阅操作");
    Thread.sleep(500);
    System.out.println("第二个订阅者完成订阅操作");
    autoCo.subscribe(System.out::println, e -> {}, () -> {});
  }

  // refCount设置为最少两个订阅者接入是才开始发出数据，当所有订阅者都取消时，如果不能在两秒内接入新的订阅者，则上游会断开连接。
  // 上边的例子中，随着前两个订阅者相继取消订阅，第三个订阅者及时（在2秒内）开始订阅，所以上游会继续发出数据，而且根据输出可以看出是“热序列”。
  // 当第三个订阅者取消后，第四个订阅者没能及时开始订阅，所以上游发布者断开连接。当第五个订阅者订阅之后，第四和第五个订阅者相当于开始了新一轮的订阅。
  @Test
  public void connectable_flux_ref_connect() throws InterruptedException {
    Flux<Long> source =
        Flux.interval(Duration.ofMillis(500))
            .doOnSubscribe(s -> System.out.println("上游收到订阅"))
            .doOnCancel(() -> System.out.println("上游发布者断开连接"));

    Flux<Long> refCounted = source.publish().refCount(2, Duration.ofSeconds(2));

    System.out.println("第一个订阅者订阅");
    Disposable sub1 = refCounted.subscribe(l -> System.out.println("sub1: " + l));

    TimeUnit.SECONDS.sleep(1);
    System.out.println("第二个订阅者订阅");
    Disposable sub2 = refCounted.subscribe(l -> System.out.println("sub2: " + l));

    TimeUnit.SECONDS.sleep(1);
    System.out.println("第一个订阅者取消订阅");
    sub1.dispose();

    TimeUnit.SECONDS.sleep(1);
    System.out.println("第二个订阅者取消订阅");
    sub2.dispose();

    TimeUnit.SECONDS.sleep(1);
    System.out.println("第三个订阅者订阅");
    Disposable sub3 = refCounted.subscribe(l -> System.out.println("sub3: " + l));

    TimeUnit.SECONDS.sleep(1);
    System.out.println("第三个订阅者取消订阅");
    sub3.dispose();

    TimeUnit.SECONDS.sleep(3);
    System.out.println("第四个订阅者订阅");
    Disposable sub4 = refCounted.subscribe(l -> System.out.println("sub4: " + l));
    TimeUnit.SECONDS.sleep(1);
    System.out.println("第五个订阅者订阅");
    Disposable sub5 = refCounted.subscribe(l -> System.out.println("sub5: " + l));
    TimeUnit.SECONDS.sleep(2);
  }
}
