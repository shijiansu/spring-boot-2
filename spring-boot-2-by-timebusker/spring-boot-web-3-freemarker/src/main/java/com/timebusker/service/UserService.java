package com.timebusker.service;

import com.timebusker.entity.User;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

  private static Map<String, User> users = new HashMap<>();

  static {
    User u1 = new User("1", "timebusker", "123456");
    User u2 = new User("2", "yujiaojiao", "123456");
    User u3 = new User("3", "mine", "123456");
    User u4 = new User("4", "admin", "123456");
    User u5 = new User("5", "popo", "123456");
    users.put(u1.getUid(), u1);
    users.put(u2.getUid(), u2);
    users.put(u3.getUid(), u3);
    users.put(u4.getUid(), u4);
    users.put(u5.getUid(), u5);
    log.info("##########################################################");
    log.info(users.values().iterator().next().getUsername());
    log.info("##########################################################");
  }

  public Map<String, User> allUser() {
    return users;
  }

  public User getUser() {
    return users.get("1");
  }

  public Map<String, User> updateUser(User u) {
    users.remove(u.getUid());
    users.put(u.getUid(), u);
    return users;
  }

  public Map<String, User> deleteUser(User u) {
    users.remove(u.getUid());
    return users;
  }
}
