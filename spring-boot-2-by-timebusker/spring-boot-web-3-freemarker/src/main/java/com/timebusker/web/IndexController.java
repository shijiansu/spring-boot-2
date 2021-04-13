package com.timebusker.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class IndexController {

  @GetMapping({"", "/", "index", "/home"})
  public String index() {
    log.info("This is the First Page.");
    return "index";
  }
}
