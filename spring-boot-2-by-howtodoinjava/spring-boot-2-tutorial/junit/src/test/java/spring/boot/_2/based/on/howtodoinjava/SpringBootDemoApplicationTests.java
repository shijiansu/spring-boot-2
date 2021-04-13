package spring.boot._2.based.on.howtodoinjava;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

// test with junit4
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringBootDemoApplicationTests {

  @LocalServerPort
  int randomServerPort;

  @Test
  public void testGetEmployeeListSuccess() throws URISyntaxException {
    RestTemplate restTemplate = new RestTemplate();

    final String baseUrl = "http://localhost:" + randomServerPort + "/employees/";
    URI uri = new URI(baseUrl);

    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

    //Verify request succeed
    assertEquals(200, result.getStatusCodeValue());
    assertTrue(Objects.requireNonNull(result.getBody()).contains("employeeList"));
  }

  @Test
  public void testAddEmployeeMissingHeader() throws URISyntaxException {
    RestTemplate restTemplate = new RestTemplate();
    final String baseUrl = "http://localhost:" + randomServerPort + "/employees/";
    URI uri = new URI(baseUrl);
    Employee employee = new Employee(null, "Adam", "Gilly", "test@email.com");

    HttpHeaders headers = new HttpHeaders();
    HttpEntity<Employee> request = new HttpEntity<>(employee, headers);

    try {
      restTemplate.postForEntity(uri, request, String.class);
      fail();
    } catch (HttpClientErrorException ex) {
      //Verify bad request and missing header
      assertEquals(400, ex.getRawStatusCode());
      assertTrue(ex.getResponseBodyAsString().contains("Missing request header"));
    }
  }
}
