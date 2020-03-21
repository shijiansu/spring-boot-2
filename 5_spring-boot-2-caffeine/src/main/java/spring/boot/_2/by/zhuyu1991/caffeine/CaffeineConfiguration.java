package spring.boot._2.by.zhuyu1991.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CaffeineConfiguration {
  public static final int DEFAULT_MAXSIZE = 10000;
  public static final int DEFAULT_TTL = 600;

  public static final String LIST_USERS = "listUsers";
  public static final String GET_USER_BY_ID = "getUserById";

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  static class CacheConfig {
    private String name;
    private int ttl = DEFAULT_TTL;
    private int maxSize = DEFAULT_MAXSIZE;
  }

  // CaffeineCacheManager is more on providing global setting for cache, and not fexible
  // So using the SimpleCacheManager with cache objects
  @Bean
  // @Primary
  public CacheManager caffeineCacheManager() {
    SimpleCacheManager cacheManager = new SimpleCacheManager();
    cacheManager.setCaches(
        Stream.of(
                new CacheConfig(LIST_USERS, DEFAULT_TTL, DEFAULT_MAXSIZE),
                new CacheConfig(GET_USER_BY_ID, 5, 100))
            .map(
                cc ->
                    new CaffeineCache(
                        cc.getName(),
                        Caffeine.newBuilder()
                            .recordStats()
                            .expireAfterWrite(cc.getTtl(), TimeUnit.SECONDS)
                            .maximumSize(cc.getMaxSize())
                            .build()))
            .collect(Collectors.toList()));
    return cacheManager;
  }
}
