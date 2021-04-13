package spring.boot._2.by.zhuyu1991.caffeine;

import static spring.boot._2.by.zhuyu1991.caffeine.CaffeineConfiguration.GET_USER_BY_ID;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.cache.annotation.Cacheable;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Cacheable(GET_USER_BY_ID)
public @interface GetUserByIdCache {}
