package spring.boot._2.by.zhuyu1991.caffeine;

import java.util.Collection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Import;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
@Import(CaffeineConfiguration.class)
public class _1_CacheManagerTest {

  @Autowired CacheManager cacheManager;

  @Order(1)
  @Test
  public void cache_size() {
    Collection<String> cacheNames = cacheManager.getCacheNames();
    cacheNames.forEach(System.out::println);
    Assertions.assertEquals(2, cacheNames.size());
  }
}
