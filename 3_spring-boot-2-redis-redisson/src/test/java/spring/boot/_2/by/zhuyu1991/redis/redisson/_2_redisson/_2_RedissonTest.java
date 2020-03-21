package spring.boot._2.by.zhuyu1991.redis.redisson._2_redisson;

import org.springframework.context.annotation.Import;
import spring.boot._2.by.zhuyu1991.redis.redisson.EmbeddedRedisConfiguration;

@Import(EmbeddedRedisConfiguration.class)
public class _2_RedissonTest extends _2_RedissonManualTest {}
