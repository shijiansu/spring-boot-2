package spring.boot._2.test.by.howtodoinjava.springboot._1_uni_test_with_mock;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import spring.boot._2.test.by.howtodoinjava.springboot.Employee;
import spring.boot._2.test.by.howtodoinjava.springboot.EmployeeController;
import spring.boot._2.test.by.howtodoinjava.springboot.EmployeeList;
import spring.boot._2.test.by.howtodoinjava.springboot.EmployeeService;

@ActiveProfiles("unit")
@ExtendWith(MockitoExtension.class)
public class _1_EmployeeControllerTest {
  @InjectMocks
  EmployeeController controller; // object of inject mocks into
  @Mock
  EmployeeService service;

  @Test
  public void create() {
    // given
    MockHttpServletRequest request = new MockHttpServletRequest(); // for CREATED response
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request)); // for CREATED response
    Employee employee = new Employee(1, "Name", 1000);

    when(service.create(any(Employee.class))).thenReturn(new Employee(1, employee));

    // when
    ResponseEntity<String> response = controller.create(employee);

    // then
    assertEquals(201, response.getStatusCodeValue());
    assertEquals("/1", requireNonNull(response.getHeaders().getLocation()).getPath());
  }

  @Test
  public void employees() {
    // given
    Employee employee1 = new Employee(1, "Name 1", 1000);
    Employee employee2 = new Employee(2, "name 2", 2000);
    List<Employee> employees = Arrays.asList(employee1, employee2);

    when(service.findList()).thenReturn(new EmployeeList(employees));

    // when
    EmployeeList response = controller.findList();

    // then
    assertEquals(2, response.getEmployees().size());
    assertEquals(employee1, response.getEmployees().get(0));
    assertEquals(employee2, response.getEmployees().get(1));
  }
}
