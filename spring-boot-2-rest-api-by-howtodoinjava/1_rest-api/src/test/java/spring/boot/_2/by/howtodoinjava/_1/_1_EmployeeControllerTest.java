package spring.boot._2.by.howtodoinjava._1;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ExtendWith(MockitoExtension.class)
public class _1_EmployeeControllerTest {

  @InjectMocks EmployeeController employeeController;

  @Mock EmployeeDAO employeeDAO;

  @Test
  public void add_employee() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

    when(employeeDAO.getAllEmployees()).thenReturn(new Employees());
    when(employeeDAO.addEmployee(any(Employee.class))).thenReturn(true);

    // when
    Employee employee = new Employee(1, "Lokesh", "Gupta", "howtodoinjava@gmail.com");
    ResponseEntity<String> responseEntity = employeeController.addEmployee(employee);

    // then
    assertEquals(201, responseEntity.getStatusCodeValue());
    assertEquals("/1", responseEntity.getHeaders().getLocation().getPath());
  }

  @Test
  public void employees() {
    // given
    Employee employee1 = new Employee(1, "Lokesh", "Gupta", "howtodoinjava@gmail.com");
    Employee employee2 = new Employee(2, "Alex", "Gussin", "example@gmail.com");
    Employees employees = new Employees();
    employees.setEmployeeList(asList(employee1, employee2));

    when(employeeDAO.getAllEmployees()).thenReturn(employees);

    // when
    Employees result = employeeController.getEmployees();

    // then
    assertEquals(2, result.getEmployeeList().size());
    assertEquals(employee1.getFirstName(), result.getEmployeeList().get(0).getFirstName());
    assertEquals(employee2.getFirstName(), result.getEmployeeList().get(1).getFirstName());
  }
}
