package spring.boot._2.test.by.howtodoinjava.springboot._4_integration_test_with_mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import spring.boot._2.test.by.howtodoinjava.springboot.EmployeeList;

@ActiveProfiles("integration")
// no need to do @SpringBootTest(classes = Application.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class _1_EmployeeControllerDownstreamIntegrationTest {
  @LocalServerPort int port;

  // 发送请求
  @Autowired TestRestTemplate client;

  @Autowired ObjectMapper mapper;

  @MockBean // no need use InjectMocks
  RestTemplate restTemplate;

  @SneakyThrows
  private String json(String location) {
    Path path =
        Paths.get(
            Objects.requireNonNull(getClass().getClassLoader().getResource(location)).toURI());
    return String.join("", Files.readAllLines(path));
  }

  @Test
  public void employees_testRestTemplate_request_and_mock_downstrem_response() throws IOException {
    // 模拟响应
    Mockito.when(restTemplate.getForObject("http://example.com/employeelist", EmployeeList.class))
        .thenReturn(mapper.readValue(json("data/employeelist.json"), EmployeeList.class));

    EmployeeList employees =
        client.getForObject("http://localhost:" + port + "/employees", EmployeeList.class);

    assertEquals(1, employees.getEmployees().size());
    assertEquals(1, employees.getEmployees().get(0).getId());
    assertEquals("Name 1", employees.getEmployees().get(0).getName());
    assertEquals(1000, employees.getEmployees().get(0).getSalary());

    Mockito.verify(restTemplate, times(1))
        .getForObject("http://example.com/employeelist", EmployeeList.class);
  }
}
