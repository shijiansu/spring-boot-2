package spring.boot._2.by.howtodoinjava._1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.net.URI;
import java.net.URISyntaxException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class _2_EmployeeControllerIntegrationTest {
  // bind the above RANDOM_PORT
  @LocalServerPort int port;

  @Autowired TestRestTemplate restTemplate;

  // In this example we do not have database
  // @Sql({ "schema.sql", "data.sql" })
  @Order(1)
  @DisplayName("Integration test for employees")
  @Test
  public void employees() throws URISyntaxException {
    // Be careful! "http://localhost:" + port + "/employees" will fail!!!
    URI uri = new URI("http://localhost:" + port + "/employees/");
    Employees employees = restTemplate.getForObject(uri, Employees.class);
    assertEquals(3, employees.getEmployeeList().size());
  }

  @Order(2)
  @Test
  public void employees2() {
    assertEquals(
        3,
        restTemplate
            .getForObject("http://localhost:" + port + "/employees/", Employees.class)
            .getEmployeeList()
            .size());
  }

  @Order(3)
  @Test
  public void add_employee() {
    assertEquals(
        201,
        restTemplate
            .postForEntity(
                "http://localhost:" + port + "/employees/",
                new Employee(Integer.MAX_VALUE, "Lokesh", "Gupta", "howtodoinjava@gmail.com"),
                String.class)
            .getStatusCodeValue());
  }
}
