package spring.boot._2.test.by.howtodoinjava.mvc.webmvc;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeList {

  List<Employee> employees;
}
