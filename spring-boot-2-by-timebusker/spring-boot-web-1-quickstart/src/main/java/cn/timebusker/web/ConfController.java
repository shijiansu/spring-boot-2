package cn.timebusker.web;

import cn.timebusker.conf.DefinitionConfig;
import cn.timebusker.conf.DubboConfig;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ConfController {

  @Resource
  private DefinitionConfig conf;

  @Resource
  private DubboConfig dubbo;

  @RequestMapping("/conf")
  public String getConfig() {
    log.info(conf.toString());
    return conf.toString();
  }

  @RequestMapping("/dubbo")
  public String dubboConfig() {
    log.info(dubbo.toString());
    return dubbo.toString();
  }
}
