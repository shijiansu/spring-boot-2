package spring.boot._2.test.by.howtodoinjava.springboot;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.OK;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class EmployeeController {

  @Autowired EmployeeService employeeService;

  @PostMapping(value = {"/create", "/"})
  public ResponseEntity<String> create(@RequestBody Employee e) {
    Employee employee = employeeService.create(e);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(employee.getId())
            .toUri();
    return ResponseEntity.created(location).build();
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Employee> findById(@PathVariable("id") Integer id) {
    return new ResponseEntity<>(employeeService.findById(id), OK);
  }

  @GetMapping(value = "/name/{name}")
  @ResponseStatus(OK)
  public Employee findByName(@PathVariable("name") String name) {
    return employeeService.findByName(name);
  }

  @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  @ResponseStatus(OK)
  public List<Employee> findAll() {
    return employeeService.findAll();
  }

  @GetMapping(value = "/list")
  @ResponseStatus(OK)
  public EmployeeList list() {
    return new EmployeeList(employeeService.findAll());
  }

  @GetMapping(value = "/employees")
  @ResponseStatus(OK)
  public EmployeeList findList() {
    return employeeService.findList();
  }

  @PutMapping(value = "/update")
  @ResponseStatus(OK)
  public Employee update(@RequestBody Employee e) {
    return employeeService.update(e);
  }

  @DeleteMapping(value = "/delete/{id}")
  @ResponseStatus(ACCEPTED)
  public void delete(@PathVariable("id") Integer id) {
    employeeService.delete(id);
  }
}
