package spring.boot._2.test.by.howtodoinjava.reactive.webflux;

import static org.mockito.Mockito.times;

import java.util.Collections;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ActiveProfiles("slicing")
@TestMethodOrder(OrderAnnotation.class)
@WebFluxTest(controllers = EmployeeController.class)
@Import(EmployeeServiceImpl.class) // slicing testing till service, mock repository
public class _1_EmployeeControllerSlicingTest {
  @MockBean EmployeeRepository repository;

  @Autowired WebTestClient client; // DefaultWebTestClient

  @Test
  @Order(1)
  public void create() {
    Employee employee = new Employee(1, "Name 1", 1000);
    Mockito.when(repository.save(employee)).thenReturn(Mono.just(employee));

    client
        .post()
        .uri("/create")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(employee))
        .exchange()
        .expectStatus()
        .isCreated();

    Mockito.verify(repository, times(1)).save(employee);
  }

  @Test
  @Order(2)
  public void employee_by_name() {
    Employee employee = new Employee(1, "Name 1", 1000);
    Flux<Employee> employeeFlux = Flux.fromIterable(Collections.singletonList(employee));
    Mockito.when(repository.findByName("Name 1")).thenReturn(employeeFlux);

    client
        .get()
        .uri("/name/{name}", "Name 1")
        .header(HttpHeaders.ACCEPT, "application/json")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(Employee.class);

    Mockito.verify(repository, times(1)).findByName("Name 1");
  }

  @Test
  @Order(3)
  public void employee() {
    Employee employee = new Employee(1, "Name 1", 1000);
    Mockito.when(repository.findById(1)).thenReturn(Mono.just(employee));

    client
        .get()
        .uri("/{id}", 1)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .jsonPath("$.name")
        .isNotEmpty()
        .jsonPath("$.id")
        .isEqualTo(1)
        .jsonPath("$.name")
        .isEqualTo("Name 1")
        .jsonPath("$.salary")
        .isEqualTo(1000);

    Mockito.verify(repository, times(1)).findById(1);
  }

  @Test
  @Order(4)
  void delete() {
    Mockito.when(repository.deleteById(1)).thenReturn(Mono.empty());

    client.delete().uri("/delete/{id}", 1).exchange().expectStatus().isAccepted();
  }
}
