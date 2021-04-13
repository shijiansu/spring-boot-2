package spring.boot._2.by.zhuyu1991.redis.redisson;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class RedisProperties {
  private int redisPort;
  private String redisHost;

  public RedisProperties(
      @Value("${spring.redis.port}") int redisPort,
      @Value("${spring.redis.host}") String redisHost) {
    this.redisPort = redisPort;
    this.redisHost = redisHost;
  }
}
