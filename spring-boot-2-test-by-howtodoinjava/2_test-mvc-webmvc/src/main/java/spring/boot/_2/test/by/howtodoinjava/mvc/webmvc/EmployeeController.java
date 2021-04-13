package spring.boot._2.test.by.howtodoinjava.mvc.webmvc;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.OK;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

  @PostMapping(value = {"/create", "/"})
  @ResponseStatus(HttpStatus.CREATED)
  public Employee create(@RequestBody Employee e) {
    return new Employee(new Random().nextInt(), e.getName(), e.getSalary());
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Employee> findById(@PathVariable("id") Integer id) {
    return new ResponseEntity<>(new Employee(id, "Name 2", 2000), OK);
  }

  @GetMapping(value = "/name/{name}")
  @ResponseStatus(OK)
  public Employee findByName(@PathVariable("name") String name) {
    return new Employee(3, name, 3000);
  }

  @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  @ResponseStatus(OK)
  public List<Employee> findAll() {
    return Collections.singletonList(new Employee(1, "Name 1", 1000));
  }

  @GetMapping(value = "/employees")
  @ResponseStatus(OK)
  public EmployeeList findList() {
    return new EmployeeList(Collections.singletonList(new Employee(1, "Name 1", 1000)));
  }

  @PutMapping(value = "/update")
  @ResponseStatus(OK)
  public Employee update(@RequestBody Employee e) {
    return e;
  }

  @DeleteMapping(value = "/delete/{id}")
  @ResponseStatus(ACCEPTED)
  public void delete(@PathVariable("id") Integer id) {}

  private TaskExecutor taskExecutor;

  public EmployeeController(TaskExecutor taskExecutor) {
    this.taskExecutor = taskExecutor;
  }

  @GetMapping(value = "/future")
  public CompletableFuture<String> future() {
    return CompletableFuture.supplyAsync(
        () -> {
          try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
          return "Hello Future!";
        },
        taskExecutor);
  }
}
