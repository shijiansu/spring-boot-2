package spring.boot._2.by.howtodoinjava._1;

import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("employees")
public class EmployeeController {

  Logger l = LoggerFactory.getLogger(EmployeeController.class);

  @Autowired private EmployeeDAO employeeDao;

  @GetMapping(path = "/", produces = "application/json")
  public Employees getEmployees() {
    Employees employees = employeeDao.getAllEmployees();
    l.info("employees: {}", employees);
    return employees;
  }

  @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
  public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
    Integer id = employeeDao.getAllEmployees().getEmployeeList().size() + 1;
    employee.setId(id);

    employeeDao.addEmployee(employee);

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(employee.getId())
            .toUri();
    return ResponseEntity.created(location).build();
  }
}
