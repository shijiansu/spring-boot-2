package spring.boot._2.test.by.howtodoinjava.mvc.restclinet;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  public RestTemplate getRestTemplate() {
    return restTemplate;
  }

  // self define http client in resttemplate
  @Autowired RestTemplate restTemplate;

  public EmployeeList findList() {
    return restTemplate.getForObject("http://example.com/employeelist", EmployeeList.class);
  }

  public List<Employee> findAll() {
    return Arrays.asList(
        Objects.requireNonNull(
            restTemplate.getForObject("http://example.com/employees", Employee[].class)));
  }
}
