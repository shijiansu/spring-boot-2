package spring.boot._2.test.by.howtodoinjava.data.jpa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("slicing")
@DataJpaTest
public class _1_EmployeeRepositorySlicingTest {
  @Autowired EmployeeRepository repository;

  @Test
  public void create() {
    Employee employee = new Employee(null, "Name 4", 4000);
    Employee e = repository.save(employee);
    assertNotNull(e.getId());
    assertThat(e.getId(), isA(Integer.class));
    assertEquals(employee.getName(), e.getName());
    assertEquals(employee.getSalary(), e.getSalary());
  }
}
