package spring.boot._2.by.zhuyu1991.caffeine;

import static spring.boot._2.by.zhuyu1991.caffeine.CaffeineConfiguration.GET_USER_BY_ID;

import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

@Slf4j
public class UserServiceImpl implements UserService {

  @Override
  @SneakyThrows
  @Cacheable(value = GET_USER_BY_ID, key = "#p0") // first parameter as key
  public String find(String name) {
    TimeUnit.SECONDS.sleep(2);
    log.info("name not from cache: {}", name);
    return name;
  }

  @Override
  @SneakyThrows
  @Cacheable(value = GET_USER_BY_ID, key = "#user.id")
  public User find(User user) {
    TimeUnit.SECONDS.sleep(2);
    log.info("user not from cache: {}", user);
    return user;
  }

  @Override
  @SneakyThrows
  @Cacheable(value = GET_USER_BY_ID, key = "#user.id", condition = "#user.id%2==0")
  public User findEven(User user) {
    TimeUnit.SECONDS.sleep(2);
    log.info("user not from cache: {}", user);
    return user;
  }

  @Override
  @CacheEvict(value = GET_USER_BY_ID, allEntries = true) // clear all
  public void delete() {
    log.info("delete all cached items");
  }

  // @CacheEvict(value="users", beforeInvocation=true) // before invoke method then clean cache
  @Override
  @CacheEvict(value = GET_USER_BY_ID, key = "#id")
  public void delete(int id) {
    log.info("delete id: {}", id);
  }

  @Override
  @SneakyThrows
  @CachePut(value = GET_USER_BY_ID, key = "#user.id")
  public User put(User user) {
    TimeUnit.SECONDS.sleep(2);
    log.info("user not from cache: {}", user);
    return user;
  }

  @Override
  @SneakyThrows
  @GetUserByIdCache
  public User findByAnnotation(User user) {
    TimeUnit.SECONDS.sleep(2);
    log.info("user not from cache: {}", user);
    return user;
  }
}
