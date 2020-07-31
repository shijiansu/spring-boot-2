package spring.boot._2.by.howtodoinjava._4;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("employees")
public class EmployeeController {

  @Autowired
  private EmployeeDAO employeeDao;

  @GetMapping(path = "/", produces = "application/json")
  public Employees getEmployees() {
    return employeeDao.getAllEmployees();
  }

  @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
  public ResponseEntity<Object> addEmployee(
      // accepts below headers
      @RequestHeader(name = "X-COM-PERSIST") String headerPersist,
      @RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "ASIA") String headerLocation,
      @RequestBody Employee employee) {
    //Generate resource id
    Integer id = employeeDao.getAllEmployees().getEmployeeList().size() + 1;
    employee.setId(id);

    //add resource
    employeeDao.addEmployee(employee);

    //Create resource location - bases on current URI
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        // append the pattern of /{id}
        .path("/{id}")
        // update /{id} value
        .buildAndExpand(employee.getId())
        .toUri();

    //Send location in response
    return ResponseEntity.created(location).build();
  }
}