package spring.boot._2.by.zhuyu1991.redis.redisson._2_redisson;

import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@Slf4j
@SpringBootTest
public class _2_RedissonManualTest { // need manually start docker redis
  @Autowired RedissonClient redissonClient;
  @Autowired StringRedisTemplate stringRedisTemplate;

  int totalStock = 100;
  String redisKey = "stock_product1";
  String lockKey = "stock_product1_lock";

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
        RLock lock = redissonClient.getLock(lockKey);
        // lock for 60 seconds, after that auto release
        lock.lock(5, TimeUnit.SECONDS);

        int stock =
            Integer.parseInt(
                Objects.requireNonNull(stringRedisTemplate.opsForValue().get(redisKey)));

        log.info(
            "[Consumer {}] - lock key: {}, remaining stock: {} - locking",
            consumer,
            lockKey,
            stock);
        if (stock > 0) {
          String newStock = (stock - 1) + "";
          stringRedisTemplate.opsForValue().set(redisKey, newStock);

          simulateConsumerProcess();
          counter.get(consumer).incrementAndGet();
          log.info(
              "[Consumer {}] - lock key: {}, remaining stock: {} - unlock",
              consumer,
              lockKey,
              newStock);
        } else {
          lock.unlock();
          break;
        }
        lock.unlock();
      }
      latch.countDown();
    };
  }

  @SneakyThrows
  @Test
  public void consume_with_distributed_redis_lock() {
    int total = 5;
    CountDownLatch latch = new CountDownLatch(total);
    IntStream.range(0, total).forEachOrdered(i -> new Thread(consumeStock(i, latch)).start());

    latch.await(total, TimeUnit.MINUTES);

    log.info("--------------------------------------------------------------------------------");
    IntStream.range(0, total)
        .forEachOrdered(i -> log.info("Sold to Consumer {}: {}", i, counter.get(i)));

    int totalSold = counter.stream().mapToInt(AtomicInteger::get).reduce(0, Integer::sum);
    log.info("Total sold: {}", totalSold);
    assertEquals(totalStock, totalSold);
  }
}
