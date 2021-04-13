package com.timebusker.web;

import com.timebusker.entity.User;
import com.timebusker.service.UserService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

  @Autowired
  private UserService service;

  @RequestMapping("/entity")
  public ModelAndView index() {
    ModelAndView mv = new ModelAndView();
    int sum = 12 + 13;
    User u = service.getUser();
    Map<String, User> users = service.allUser();
    Collection<User> cu = service.allUser().values();
    List<User> lu = new ArrayList<>(cu);
    mv.addObject("sum", sum);
    mv.addObject("u", u);
    mv.addObject("hu", users);
    mv.addObject("lu", lu);
    return mv;
  }
}
