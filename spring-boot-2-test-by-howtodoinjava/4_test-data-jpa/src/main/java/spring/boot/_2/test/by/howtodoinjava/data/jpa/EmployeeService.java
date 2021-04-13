package spring.boot._2.test.by.howtodoinjava.data.jpa;

import java.util.List;

public interface EmployeeService {
  Employee create(Employee e);

  Employee findById(Integer id);

  Employee findByName(String name);

  List<Employee> findAll();

  Employee update(Employee e);

  Void delete(Integer id);
}
