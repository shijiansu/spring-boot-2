package spring.boot._2.test.by.howtodoinjava.mvc.restclinet;

import java.util.List;
import org.springframework.web.client.RestTemplate;

public interface EmployeeService {

  List<Employee> findAll();

  EmployeeList findList();

  RestTemplate getRestTemplate();
}
