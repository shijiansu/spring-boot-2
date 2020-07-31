package spring.boot._2.test.by.howtodoinjava.reactive.webflux;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Employee {

  @Id Integer id;
  String name;
  long salary;
}
