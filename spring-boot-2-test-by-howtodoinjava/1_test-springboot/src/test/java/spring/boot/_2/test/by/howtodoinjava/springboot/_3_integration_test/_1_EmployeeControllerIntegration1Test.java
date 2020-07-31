package spring.boot._2.test.by.howtodoinjava.springboot._3_integration_test;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import spring.boot._2.test.by.howtodoinjava.springboot.Employee;
import spring.boot._2.test.by.howtodoinjava.springboot.EmployeeList;

@ActiveProfiles("integration")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class _1_EmployeeControllerIntegration1Test {
  @LocalServerPort int port;
  // 发送请求
  @Autowired TestRestTemplate client;

  @Sql({"data/data.sql"}) // no need to schema as DLL auto create
  @Test
  public void employees() {
    assertEquals(
        3,
        client
            .getForObject("http://localhost:" + port + "/list", EmployeeList.class)
            .getEmployees()
            .size());
  }

  @Sql({"data/truncate.sql"})
  @Test
  public void create() {
    Employee employee = new Employee(1, "Name 1", 1000);
    ResponseEntity<String> response =
        client.postForEntity("http://localhost:" + port + "/create", employee, String.class);
    assertEquals(201, response.getStatusCodeValue());
    assertEquals("/create/1", requireNonNull(response.getHeaders().getLocation()).getPath());
  }
}
