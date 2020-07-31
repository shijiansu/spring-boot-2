package spring.boot._2.by.zhuyu1991.hikaricp;

import com.alibaba.fastjson.JSON;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
@Slf4j
public class _1_UserTest {

  @Autowired UserService userService;

  @Order(1)
  @Test
  public void add() {
    User user = new User();
    user.setName("zy");
    user.setAge(26);
    userService.add(user);
  }

  @Order(2)
  @Test
  public void edit() {
    User user = new User();
    user.setId(1);
    user.setName("zy1");
    user.setAge(22);
    userService.edit(user);
  }

  @Order(3)
  @Test
  public void findById() {
    User user = userService.findById(1);
    log.info("user content :" + JSON.toJSONString(user));
  }

  @Order(4)
  @Test
  public void findByName() {
    User user = userService.findByName("zy1");
    log.info("user content :" + JSON.toJSONString(user));
  }

  @Order(5)
  @Test
  public void findAll() {
    List<User> list = userService.findAll();
    log.info("user content :" + JSON.toJSONString(list));
  }

  @Order(6)
  @Test
  public void delete() {
    userService.delete(1);
  }
}
