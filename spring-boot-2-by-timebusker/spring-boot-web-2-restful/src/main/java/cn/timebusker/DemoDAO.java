package cn.timebusker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class DemoDAO {

  // 创建线程安全的Map
  private static Map<String, Demo> map = Collections.synchronizedMap(new HashMap<>());

  static {
    for (int i = 0; i < 10; i++) {
      Demo demo = new Demo();
      demo.setId(++i + "");
      demo.setName("mine_" + i);
      demo.setDescription("我是第" + i + "个同学！");
      map.put(i + "", demo);
    }
  }

  public List<Demo> insert(Demo demo) {
    map.put(demo.getId(), demo);
    return getList(map);
  }

  public Demo find(String id) {
    return map.get(id);
  }

  public List<Demo> update(Demo demo) {
    map.replace(demo.getId(), demo);
    return getList(map);
  }

  public List<Demo> delete(Demo demo) {
    map.remove(demo.getId());
    return getList(map);
  }

  List<Demo> findAll() {
    return getList(map);
  }

  private List<Demo> getList(Map<String, Demo> map) {
    return new ArrayList<>(map.values());
  }
}
