package spring.boot._2.by.zhuyu1991.redis.redisson;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.boot.test.context.TestConfiguration;
import redis.embedded.RedisServer;

@TestConfiguration
public class EmbeddedRedisConfiguration {
  RedisServer redisServer;

  public EmbeddedRedisConfiguration(RedisProperties redisProperties) {
    this.redisServer = new RedisServer(redisProperties.getRedisPort());
  }

  @PostConstruct
  public void postConstruct() {
    redisServer.start();
  }

  @PreDestroy
  public void preDestroy() {
    redisServer.stop();
  }
}
