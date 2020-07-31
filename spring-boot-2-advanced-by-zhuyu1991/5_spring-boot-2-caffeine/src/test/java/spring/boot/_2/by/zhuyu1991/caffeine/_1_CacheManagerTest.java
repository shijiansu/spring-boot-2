package spring.boot._2.by.zhuyu1991.caffeine;

import java.util.Collection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(CaffeineConfiguration.class)
public class _1_CacheManagerTest {

  @Autowired CacheManager cacheManager;

  @Test
  public void cache_size() {
    Collection<String> cacheNames = cacheManager.getCacheNames();
    cacheNames.forEach(System.out::println);
    Assertions.assertEquals(2, cacheNames.size());
  }
}
