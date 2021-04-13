package spring.boot._2.by.zhuyu1991.redis.redisson._2_redisson;

import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.StringRedisTemplate;
import spring.boot._2.by.zhuyu1991.redis.redisson.Application;
import spring.boot._2.by.zhuyu1991.redis.redisson.EmbeddedRedisConfiguration;

@Slf4j
@SpringBootTest
@Import(EmbeddedRedisConfiguration.class)
public class _1_RedisOnlySoldOverStockTest {

  @Autowired StringRedisTemplate stringRedisTemplate;

  int totalStock = 100;
  String redisKey = "stock_product1";

  List<AtomicInteger> counter =
      Collections.unmodifiableList(
          Arrays.asList(
              new AtomicInteger(0),
              new AtomicInteger(0),
              new AtomicInteger(0),
              new AtomicInteger(0),
              new AtomicInteger(0)));

  @BeforeEach
  public void simulateStockBeforeSale() {
    stringRedisTemplate.opsForValue().set(redisKey, valueOf(totalStock));
    assertEquals(valueOf(totalStock), stringRedisTemplate.opsForValue().get(redisKey));
  }

  @SneakyThrows
  private void simulateConsumerDelay() {
    // some of consumer start behind!!!
    Thread.sleep(new Random().nextInt(10) * 100);
  }

  @SneakyThrows
  private void simulateConsumerProcess() {
    // processing e.g. store at database
    Thread.sleep(100);
  }

  private Runnable consumeStock(int consumer, CountDownLatch latch) {
    return () -> {
      for (int i = 0; i < 55; i++) {
        int stock =
            Integer.parseInt(
                Objects.requireNonNull(stringRedisTemplate.opsForValue().get(redisKey)));

        log.info("[Consumer {}] - remaining stock: {} - no locking", consumer, stock);
        if (stock > 0) {
          String newStock = (stock - 1) + "";
          stringRedisTemplate.opsForValue().set(redisKey, newStock);

          simulateConsumerProcess();
          counter.get(consumer).incrementAndGet();
          log.info("[Consumer {}] - remaining stock: {} - no need unlock", consumer, newStock);
        } else {
          break;
        }
      }
      latch.countDown();
    };
  }

  @SneakyThrows
  @Test
  public void consume_sold_over_stock() {
    int total = 5;
    CountDownLatch latch = new CountDownLatch(total);
    IntStream.range(0, total).forEachOrdered(i -> new Thread(consumeStock(i, latch)).start());

    latch.await(total, TimeUnit.MINUTES);

    log.info("--------------------------------------------------------------------------------");
    IntStream.range(0, total)
        .forEachOrdered(i -> log.info("Sold to Consumer {}: {}", i, counter.get(i)));

    int totalSold = counter.stream().mapToInt(AtomicInteger::get).reduce(0, Integer::sum);
    log.info("Total sold: {}", totalSold);
    assertTrue(totalStock < totalSold);
  }
}
