package cn.timebusker;

import static java.lang.System.lineSeparator;

import cn.timebusker.service.OrderService;
import cn.timebusker.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CommonController {

  @Autowired
  private CommonService service;

  @Autowired
  private OrderService orderService;

  @Autowired
  private UserService userService;

  @RequestMapping(value = {"/**", "/"}, method = RequestMethod.GET)
  public Long test() {
    int i = 1;
    i = CommonUtil.add(i);
    i = service.add(i);
    log.info("====================i: {}===================={}", i, lineSeparator());
    String name = "#####";
    name = orderService.addOrder(name);
    name = userService.addUser(name);
    log.info("====================name: {}===================={}", name, lineSeparator());
    return System.currentTimeMillis();
  }

  @GetMapping("/user")
  public Long user() {
    String name = "#####";
    name = userService.addUser(name);
    log.info("====================name: {}===================={}", name, lineSeparator());
    return System.currentTimeMillis();
  }
}
