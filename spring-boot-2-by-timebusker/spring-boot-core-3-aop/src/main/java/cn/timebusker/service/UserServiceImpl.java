package cn.timebusker.service;

import cn.timebusker.annotation.TimebuskerMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

  @Override
  @TimebuskerMethod
  public String addUser(String name) {
    log.info("New user name: {}", name);
    return name + "U";
  }
}