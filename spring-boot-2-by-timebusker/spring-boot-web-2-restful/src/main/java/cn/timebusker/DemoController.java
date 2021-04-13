package cn.timebusker;

import com.alibaba.fastjson.JSON;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

  @Resource
  private DemoService service;

  // @RequestMapping(value = {"/demo"}, method = RequestMethod.POST)
  @PostMapping("/demo")
  public List<Demo> add(@ModelAttribute Demo demo) {
    System.out.println(JSON.toJSONString(demo));
    return service.insert(demo);
  }

  @RequestMapping(value = {"/demo-requestParam"}, method = RequestMethod.POST)
  public List<Demo> insert(@RequestParam("id") String id,
      @RequestParam("name") String name, @RequestParam("description") String description) {
    Demo demo = new Demo();
    demo.setId(id);
    demo.setDescription(description);
    demo.setName(name);
    System.out.println(JSON.toJSONString(demo));
    return service.insert(demo);
  }

  @RequestMapping(value = "/demo/{id}", method = RequestMethod.GET)
  public Demo find(@PathVariable String id) {
    return service.find(id);
  }

  @RequestMapping(value = "/demo/{id}", method = RequestMethod.PUT)
  public List<Demo> update(@PathVariable String id) {
    Demo demo = new Demo();
    demo.setId(id);
    demo.setDescription("我是第" + id + "ST");
    demo.setName("TS__" + id);
    return service.update(demo);
  }

  @RequestMapping(value = "/demo/{id}", method = RequestMethod.DELETE)
  public List<Demo> delete(@PathVariable String id) {
    Demo demo = new Demo();
    demo.setId(id);
    return service.delete(demo);
  }

  @RequestMapping(value = {"/demos", "/"}, method = RequestMethod.GET)
  public List<Demo> findAll() {
    return service.findAll();
  }

  @ResponseBody
  @RequestMapping("/sum")
  public void sum() throws ArithmeticException {
    throw new ArithmeticException("some exception happened");
  }
}
