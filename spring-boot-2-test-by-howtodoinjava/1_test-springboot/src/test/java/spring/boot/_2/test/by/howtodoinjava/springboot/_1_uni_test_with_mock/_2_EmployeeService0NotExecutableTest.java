package spring.boot._2.test.by.howtodoinjava.springboot._1_uni_test_with_mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import spring.boot._2.test.by.howtodoinjava.springboot.Employee;
import spring.boot._2.test.by.howtodoinjava.springboot.EmployeeRepository;
import spring.boot._2.test.by.howtodoinjava.springboot.EmployeeServiceImpl;

public class _2_EmployeeService0NotExecutableTest {
  @InjectMocks
  EmployeeServiceImpl service;

  @Mock
  EmployeeRepository repository;

  @Test
  public void employees() {
    // given
    Employee employee1 = new Employee(1, "Name 1", 1000);
    Employee employee2 = new Employee(2, "name 2", 2000);
    Employee employee3 = new Employee(3, "Name 3", 3000);
    List<Employee> list = Arrays.asList(employee1, employee2, employee3);

    when(repository.findAll()).thenReturn(list);

    // when
    List<Employee> employees = service.findAll();

    // then
    assertEquals(3, employees.size());
    assertEquals(list, employees);
    verify(repository, times(1)).findAll();
  }

  @Test
  public void employee() {
    when(repository.findById(1)).thenReturn(Optional.of(new Employee(1, "Name 1", 1000)));

    Employee employee = service.findById(1);

    assertEquals(1, employee.getId());
    assertEquals("Name 1", employee.getName());
    assertEquals(1000, employee.getSalary());
  }

  @Test
  public void create() {
    Employee employee = new Employee(1, "Name 1", 1000);
    service.create(employee);
    verify(repository, times(1)).save(employee);
  }
}
