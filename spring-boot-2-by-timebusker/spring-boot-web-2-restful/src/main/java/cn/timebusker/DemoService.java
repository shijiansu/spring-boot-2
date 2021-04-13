package cn.timebusker;

import java.util.List;

public interface DemoService {

	List<Demo> insert(Demo demo);
	
	Demo find(String id);
	
	List<Demo> update(Demo demo);
	
	List<Demo> delete(Demo demo);
	
	List<Demo> findAll();
}
