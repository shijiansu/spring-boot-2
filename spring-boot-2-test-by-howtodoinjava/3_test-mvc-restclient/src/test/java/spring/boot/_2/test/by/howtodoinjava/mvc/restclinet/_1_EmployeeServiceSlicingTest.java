package spring.boot._2.test.by.howtodoinjava.mvc.restclinet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.client.MockRestServiceServer;

@ActiveProfiles("slicing")
@RestClientTest(EmployeeServiceImpl.class) // service
@AutoConfigureWebClient(registerRestTemplate = true) // init resttemplate also
public class _1_EmployeeServiceSlicingTest {
  @Autowired MockRestServiceServer downstream;

  @Autowired EmployeeService service;

  @Test
  public void load_rest_template() {
    // will not be init if no @AutoConfigureWebClient
    assertNotNull(service.getRestTemplate());
  }

  @SneakyThrows
  private String json(String location) {
    Path path =
        Paths.get(
            Objects.requireNonNull(getClass().getClassLoader().getResource(location)).toURI());
    return String.join("", Files.readAllLines(path));
  }

  @Test
  public void employees() {
    downstream
        .expect(requestTo("http://example.com/employees"))
        .andRespond(withSuccess(json("data/employees.json"), MediaType.APPLICATION_JSON));

    List<Employee> response = service.findAll();
    List<Employee> employees = Collections.singletonList(new Employee(1, "Name 1", 1000));

    assertEquals(employees, response);
  }

  @Test
  public void employee_list() throws IOException, URISyntaxException {
    downstream
        .expect(requestTo("http://example.com/employeelist"))
        .andRespond(withSuccess(json("data/employeelist.json"), MediaType.APPLICATION_JSON));

    EmployeeList response = service.findList();
    EmployeeList employees =
        new EmployeeList(Collections.singletonList(new Employee(1, "Name 1", 1000)));

    assertEquals(employees, response);
  }
}
