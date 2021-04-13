package spring.boot._2.by.zhuyu1991.redis.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfiguration {

  @Bean
  RedissonClient redissonClient() {
    // RedissonClient redisson = Redisson.create(); // default to 127.0.0.1:6379
    Config config = new Config();
    config.useSingleServer().setAddress("redis://localhost:6379");
    return Redisson.create(config);
  }
}
