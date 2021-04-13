package cn.timebusker;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {

  @Resource
  private DemoDAO dao;

  @Override
  public List<Demo> insert(Demo demo) {
    return dao.insert(demo);
  }

  @Override
  public Demo find(String id) {
    return dao.find(id);
  }

  @Override
  public List<Demo> update(Demo demo) {
    return dao.update(demo);
  }

  @Override
  public List<Demo> delete(Demo demo) {
    return dao.delete(demo);
  }

  @Override
  public List<Demo> findAll() {
    return dao.findAll();
  }

}
