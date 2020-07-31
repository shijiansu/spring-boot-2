package spring.boot._2.test.by.howtodoinjava.reactive.webflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired EmployeeRepository employeeRepo;

  public void create(Employee e) {
    employeeRepo.save(e).subscribe();
  }

  public Mono<Employee> findById(Integer id) {
    return employeeRepo.findById(id);
  }

  public Flux<Employee> findByName(String name) {
    return employeeRepo.findByName(name);
  }

  public Flux<Employee> findAll() {
    return employeeRepo.findAll();
  }

  public Mono<Employee> update(Employee e) {
    return employeeRepo.save(e);
  }

  public Mono<Void> delete(Integer id) {
    return employeeRepo.deleteById(id);
  }
}
