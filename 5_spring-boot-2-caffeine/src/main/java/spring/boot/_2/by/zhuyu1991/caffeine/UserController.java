package spring.boot._2.by.zhuyu1991.caffeine;

import static spring.boot._2.by.zhuyu1991.caffeine.CaffeineConfiguration.LIST_USERS;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("cache")
public class UserController {

  @SneakyThrows
  @GetMapping("list/{length}")
  @Cacheable(value = LIST_USERS, key = "#length", sync = true)
  public List<User> list(@PathVariable Integer length) {
    TimeUnit.SECONDS.sleep(5);
    return IntStream.range(0, length)
        .mapToObj(i -> new User(i, "zhuyu" + i, 20 + i))
        .collect(Collectors.toList());
  }

  @GetMapping("delete")
  @CacheEvict(value = LIST_USERS, allEntries = true) // clear all
  public void delete() {
    log.info("delete all cached items");
  }
}
