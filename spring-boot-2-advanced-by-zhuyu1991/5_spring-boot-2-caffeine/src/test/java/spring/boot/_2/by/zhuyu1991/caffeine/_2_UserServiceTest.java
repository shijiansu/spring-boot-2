package spring.boot._2.by.zhuyu1991.caffeine;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Import;
import org.springframework.util.StopWatch;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = UserServiceImpl.class)
@Import(CaffeineConfiguration.class)
public class _2_UserServiceTest {

  @Autowired UserService userService;
  @Autowired CacheManager cacheManager;

  StopWatch watcher;

  @BeforeEach
  public void watcher() {
    watcher = new StopWatch();
  }

  @AfterEach
  public void clean() {
    cacheManager.getCacheNames().forEach(n -> cacheManager.getCache(n).clear());
  }

  @Order(1)
  @Test
  public void load_userservice() {
    assertNotNull(userService);
  }

  @Order(2)
  @Test
  public void find_by_name_2nd_time_no_sleeping() {
    String name1 = userService.find("Mike");
    final String[] name2 = new String[1];
    assertTimeout(
        ofSeconds(2),
        () -> {
          name2[0] = userService.find("Mike");
        });
    assertEquals(name1, name2[0]);
  }

  @Order(3)
  @Test
  public void find_2nd_time_no_sleeping() {
    User user1 = userService.find(new User(1, "user 1", 10));
    User user2 = userService.find(new User(1, "user 1", 10));
    assertTimeout(
        Duration.ofSeconds(2),
        () -> {
          userService.find(new User(1, "user 1", 10));
        });
    assertEquals(user1, user2); // same value
    assertSame(user1, user2); // same object
  }

  @Order(4)
  @Test
  public void find_even_user_id_1_2nd_time_sleeping() {
    User user1 = userService.findEven(new User(1, "user 1", 10));
    watcher.start();
    User user2 = userService.findEven(new User(1, "user 1", 10));
    watcher.stop();
    assertTrue(watcher.getLastTaskTimeMillis() >= 2000);
    assertEquals(user1, user2); // same value
    assertNotSame(user1, user2); // different object
  }

  @Order(5)
  @Test
  public void find_even_2nd_time_no_sleeping() {
    User user1 = userService.findEven(new User(2, "user 2", 10));
    User user2 = userService.findEven(new User(2, "user 4", 10)); // name do not matter
    assertEquals(user1, user2); // same value
    assertSame(user1, user2); // different object
    assertTimeout(
        Duration.ofSeconds(2),
        () -> {
          userService.findEven(new User(2, "user 1", 10));
        });
  }

  @Order(6)
  @Test
  public void delete_all() {
    userService.find(new User(1, "user 1", 10));
    userService.find(new User(2, "user 2", 20));

    watcher.start();
    userService.find(new User(1, "user 1", 10));
    watcher.stop();
    assertTrue(watcher.getLastTaskTimeMillis() < 2000);

    userService.delete();

    watcher.start();
    userService.find(new User(2, "user 2", 20));
    watcher.stop();
    assertTrue(watcher.getLastTaskTimeMillis() >= 2000);
  }

  @Order(6)
  @Test
  public void delete() {
    userService.find(new User(1, "user 1", 10));
    User user1 = userService.find(new User(2, "user 2", 20));

    watcher.start();
    userService.find(new User(1, "user 1", 10));
    watcher.stop();
    assertTrue(watcher.getLastTaskTimeMillis() < 2000);

    userService.delete(1);

    watcher.start();
    userService.find(new User(1, "user 1", 10));
    watcher.stop();
    assertTrue(watcher.getLastTaskTimeMillis() >= 2000);

    watcher.start();
    User user2 = userService.find(new User(2, "user 2", 20));
    watcher.stop();
    assertTrue(watcher.getLastTaskTimeMillis() < 2000);
    assertSame(user1, user2); // different object
  }

  @SneakyThrows
  @Order(7)
  @Test
  public void expire_to_prove_is_managed_by_cache_object_but_not_cachemanager() {
    userService.find(new User(1, "user 1", 10));
    TimeUnit.SECONDS.sleep(6);

    watcher.start();
    userService.find(new User(1, "user 1", 10));
    watcher.stop();

    assertTrue(watcher.getLastTaskTimeMillis() >= 2000);
  }

  @SneakyThrows
  @Order(8)
  @Test
  public void put() {
    User user = new User(100, "user 1", 10);
    userService.put(user);

    watcher.start();
    userService.put(user);
    watcher.stop();

    assertTrue(watcher.getLastTaskTimeMillis() >= 2000);
  }

  @SneakyThrows
  @Order(9)
  @Test
  public void findByAnnotation() {
    find_2nd_time_no_sleeping(); // shall be the same
  }
}
