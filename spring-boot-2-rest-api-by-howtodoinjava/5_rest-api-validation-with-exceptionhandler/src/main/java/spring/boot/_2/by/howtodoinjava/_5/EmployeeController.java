package spring.boot._2.by.howtodoinjava._5;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot._2.by.howtodoinjava._5.ApplicationExceptionHandler.RecordNotFoundException;

@RestController
@RequestMapping("employees")
public class EmployeeController {

  @Autowired private EmployeeDAO employeeDao;

  @GetMapping(path = "/{id}", produces = "application/json")
  public Employee getEmployee(@PathVariable String id) {
    return employeeDao.getAllEmployees().getEmployeeList().stream()
        .filter(e -> id.equals(e.getEmployeeId().toString()))
        .findFirst()
        .orElseThrow(
            () -> new RecordNotFoundException(String.format("Employee id: %s does not exist", id)));
  }

  // Enable validation of request body by @Valid annotation
  @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
  public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
    employeeDao.addEmployee(employee);
    return new ResponseEntity<>(employee, HttpStatus.OK);
  }
}
