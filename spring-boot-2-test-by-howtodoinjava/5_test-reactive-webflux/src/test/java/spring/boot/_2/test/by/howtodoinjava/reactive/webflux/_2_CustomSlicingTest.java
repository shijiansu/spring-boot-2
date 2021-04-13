package spring.boot._2.test.by.howtodoinjava.reactive.webflux;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

@_2_CustomSlicingTester
public class _2_CustomSlicingTest {

  @Autowired RestTemplate restTemplate;
  @MockBean EmployeeRepository repository;

  @Order(1)
  @Test
  public void load_resttemplate() {
    assertNotNull(restTemplate);
  }

  @Order(2)
  @Test
  public void load_repository() {
    assertNotNull(repository);
  }
}
