package spring.boot._2.by.zhuyu1991.redis.redisson._1_redis;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.StringRedisTemplate;
import spring.boot._2.by.zhuyu1991.redis.redisson.EmbeddedRedisConfiguration;

@Slf4j
@SpringBootTest
// do not do below, it loads Application.class and makes EmbeddedRedisConfiguration not be
// destroyed. because EmbeddedRedisConfiguration not be destroyed, the embedded redis is not stop,
// but the new object of redis server cannot detect the previous one is running... as the port is
// occupied, cannot start the next redis server again, causing the mvn test fail.
// @SpringBootTest(classes = Application.class)
@Import(EmbeddedRedisConfiguration.class)
public class _1_StringRedisTemplateTest {
  @Autowired StringRedisTemplate stringRedisTemplate;

  // stringRedisTemplate.opsForSet(); // 操作set
  // stringRedisTemplate.opsForSet().isMember("keySet", "1");
  // stringRedisTemplate.opsForZSet(); // 操作有序set

  @Test
  public void add_get_delete() {
    String key = "key";
    String originalValue = "value";
    stringRedisTemplate.opsForValue().set(key, originalValue);

    String value = stringRedisTemplate.opsForValue().get(key);
    log.info("value:{}", value);
    assertEquals(originalValue, value);

    stringRedisTemplate.delete(key);
    value = stringRedisTemplate.opsForValue().get(key);
    assertNull(value);
  }

  @SneakyThrows
  @Test
  public void add_with_timeout() {
    String key = "timestamp";
    String timestamp = new Date().getTime() + "";
    stringRedisTemplate.opsForValue().set(key, timestamp, 1, TimeUnit.SECONDS);

    String value = stringRedisTemplate.opsForValue().get(key);
    assertEquals(timestamp, value);

    Thread.sleep(200); // still not timeout, able to get the value

    value = stringRedisTemplate.opsForValue().get(key);
    assertEquals(timestamp, value);

    Thread.sleep(1000); // timeout, value is gone
    value = stringRedisTemplate.opsForValue().get(key);
    assertNull(value);
  }

  @SneakyThrows
  @Test
  public void add_with_timeout2() {
    String key = "timestamp";
    String timestamp = new Date().getTime() + "";
    stringRedisTemplate.opsForValue().set(key, timestamp);

    String value = stringRedisTemplate.opsForValue().get(key);
    assertEquals(timestamp, value);

    // 设置过期时间
    stringRedisTemplate.expire(key, 1000, TimeUnit.MILLISECONDS);
    // 根据key获取过期时间
    Long expire = stringRedisTemplate.getExpire(key);
    log.info("expire: {}", expire);
    // 根据key获取过期时间并换算成指定单位
    Long expireSeconds = stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
    log.info("expireSeconds: {}", expireSeconds);

    Thread.sleep(200); // still not timeout, able to get the value

    value = stringRedisTemplate.opsForValue().get(key);
    assertEquals(timestamp, value);

    Thread.sleep(1000); // timeout, value is gone
    value = stringRedisTemplate.opsForValue().get("key");
    assertNull(value);
  }

  // list数据类型适合于消息队列的场景:比如12306并发量太高，
  // 而同一时间段内只能处理指定数量的数据！必须满足先进先出的原则，其余数据处于等待
  @Test
  public void push_list_delete_element_in_list() {
    // leftPush from last
    stringRedisTemplate.opsForList().rightPush("myList", "1");
    stringRedisTemplate.opsForList().rightPush("myList", "2");
    stringRedisTemplate.opsForList().rightPush("myList", "A");
    stringRedisTemplate.opsForList().rightPush("myList", "B");
    stringRedisTemplate.opsForList().rightPush("myList", "A");
    stringRedisTemplate.opsForList().rightPush("myList", "B");

    // leftPush from begining
    stringRedisTemplate.opsForList().leftPush("myList", "0");

    // list all
    List<String> list = stringRedisTemplate.opsForList().range("myList", 0, -1);
    log.info("list all {}", list);
    assertNotNull(list);
    assertArrayEquals(new String[] {"0", "1", "2", "A", "B", "A", "B"}, list.toArray());

    // list top 4
    list = stringRedisTemplate.opsForList().range("myList", 0, 3);
    log.info("list limit {}", list);
    assertNotNull(list);
    assertArrayEquals(new String[] {"0", "1", "2", "A"}, list.toArray());

    // delete first 1 B
    stringRedisTemplate.opsForList().remove("myList", 1, "B");
    list = stringRedisTemplate.opsForList().range("myList", 0, -1);
    log.info("list all {}", list);
    assertNotNull(list);
    assertArrayEquals(new String[] {"0", "1", "2", "A", "A", "B"}, list.toArray());

    // delete all A
    stringRedisTemplate.opsForList().remove("myList", 0, "A");
    list = stringRedisTemplate.opsForList().range("myList", 0, -1);
    log.info("list all {}", list);
    assertNotNull(list);
    assertArrayEquals(new String[] {"0", "1", "2", "B"}, list.toArray());
  }

  @Test
  public void hash_put_keys_values_delete_size() {
    String key = "banks:12600000";
    String hashKey = "a";
    String hashValue = "b";

    stringRedisTemplate.opsForHash().put(key, hashKey, hashValue);
    Object value = stringRedisTemplate.opsForHash().get(key, hashKey);
    assertEquals(hashValue, value);

    Map<Object, Object> map = stringRedisTemplate.opsForHash().entries(key);
    log.info("map: {}", map);

    Set<Object> hashKeys = stringRedisTemplate.opsForHash().keys(key);
    log.info("hashKeys: {}", hashKeys);

    List<Object> hashValues = stringRedisTemplate.opsForHash().values(key);
    log.info("values: {}", hashValues);

    long size = stringRedisTemplate.opsForHash().size(key);
    log.info("size:{}", size);
    assertEquals(1L, size);

    stringRedisTemplate.opsForHash().delete(key, hashKey);
    value = stringRedisTemplate.opsForHash().get(key, hashKey);
    assertNull(value);
    size = stringRedisTemplate.opsForHash().size(key);
    assertEquals(0L, size);
  }
}
