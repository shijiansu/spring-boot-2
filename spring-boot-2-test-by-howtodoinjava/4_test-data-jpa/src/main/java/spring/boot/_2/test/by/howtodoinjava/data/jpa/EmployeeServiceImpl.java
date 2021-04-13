package spring.boot._2.test.by.howtodoinjava.data.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired EmployeeRepository repository;

  @Override
  public Employee create(Employee e) {
    Optional<Employee> employee = repository.findById(e.getId());
    if (employee.isPresent()) {
      throw new RuntimeException("Eemployee record exist for given id");
    } else {
      return repository.save(e);
    }
  }

  @Override
  public Employee findById(Integer id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("No employee record exist for given id"));
  }

  @Override
  public Employee findByName(String name) {
    return null;
  }

  @Override
  public List<Employee> findAll() {
    return repository.findAll();
  }

  @Override
  public Employee update(Employee e) {
    Optional<Employee> employee = repository.findById(e.getId());
    if (employee.isPresent()) {
      Employee ee = employee.get();
      ee.setName(e.getName());
      ee.setSalary(e.getSalary());
      return repository.save(ee);
    } else {
      throw new RuntimeException("No employee record exist for given id");
    }
  }

  @Override
  public Void delete(Integer id) {
    Optional<Employee> employee = repository.findById(id);
    if (employee.isPresent()) {
      repository.deleteById(id);
      return null;
    } else {
      throw new RuntimeException("No employee record exist for given id");
    }
  }
}
