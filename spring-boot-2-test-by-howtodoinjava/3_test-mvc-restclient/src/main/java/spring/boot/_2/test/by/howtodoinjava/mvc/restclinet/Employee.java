package spring.boot._2.test.by.howtodoinjava.mvc.restclinet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

  Integer id;
  String name;
  long salary;
}
